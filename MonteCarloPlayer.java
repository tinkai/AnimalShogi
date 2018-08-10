import java.util.List;
import java.util.ArrayList;
public class MonteCarloPlayer extends RandomPlayer implements PieceID {
    private List<Integer> winN;
    private History history;
    private final int tryN = 500;
    private boolean win;

    MonteCarloPlayer() {}
    MonteCarloPlayer(int n, Board board) {
        super(n, board);
        this.winN = new ArrayList<Integer>();
        this.history = new History(this.board);
        this.win = false;
    }

    public void turn() {
        this.handArray.clear();
        this.winN.clear();
        super.addMove();
        super.addDrop();
        for (int i = 0; i < this.handArray.size(); i++) this.winN.add(0);
        monteCarloSearch();
        selectHand();
    }

    private void monteCarloSearch() {
        for (int i = 0; i < this.tryN; i++) {
            int rand = new java.util.Random().nextInt(this.handArray.size());
            Hand hand = this.handArray.get(rand);
            if (hand.getMove() == 0) move(hand, this.group);
            else if (hand.getMove() == 1) drop(hand, this.group);
            this.history.add(hand);
            if (isWin(this.group)) {
                this.winN.set(rand, this.winN.get(rand)+1);
                this.win = false;
                this.history.undo(); 
                continue;
            }
            this.board.changeTurn(); 
            this.board.addTurnN();       
            tmpMonteCarloSearch();
            if (this.win) this.winN.set(rand, this.winN.get(rand)+1);
            this.win = false;
            this.history.undo();
        }
        //System.out.println(this.winN);
    }

    private void tmpMonteCarloSearch() {
        List<Hand> handArray = new ArrayList<Hand>();
        addMove(handArray);
        addDrop(handArray);
        int rand = new java.util.Random().nextInt(handArray.size());
        Hand hand = handArray.get(rand);
        if (hand.getMove() == 0) move(hand, this.board.getTurn());
        else if (hand.getMove() == 1) drop(hand, this.board.getTurn());
        this.history.add(hand);
        if (isWin(this.board.getTurn())) {
            if (this.board.getTurn() == this.group) this.win = true; 
            this.board.changeTurn();   
            this.board.addTurnN();        
            this.history.undo(); 
            return;
        }
        this.board.changeTurn();
        this.board.addTurnN();       
        tmpMonteCarloSearch();
        this.history.undo();
    }

    private void addMove(List<Hand> handArray) {
        for (int x = 5; x <= 15; x += 5) {
            for (int y = 1; y <= 4; y++) {
                if (this.board.isAlly(this.board.getTurn(), x+y)) {
                    for (int tx = -5; tx <= 5; tx += 5) {
                        for (int ty = -1; ty <= 1; ty++) {
                            if (this.board.getPiece(x+y).isControl(x+y, x+y+tx+ty) && !isOut(x+y+tx+ty) && !this.board.isAlly(this.board.getTurn(), x+y+tx+ty)) {
                                Hand addHand = new Hand();
                                addHand.setMove(0);
                                addHand.setBefore(x+y);
                                addHand.setAfter(x+y+tx+ty);
                                handArray.add(addHand);
                            }
                        }
                    }
                }
            }
        }
    }

    private void addDrop(List<Hand> handArray) {
        for (int n = KIRIN; n <= HIYO; n++) {
            if (this.board.isHasPiece(this.board.getTurn(), n)) {
                for (int x = 5; x <= 15; x += 5) {
                    for (int y = 1; y <= 4; y++) {
                        if (!this.board.isPiece(x+y)) {
                            Hand addHand = new Hand(1, 0, x+y, n, 0, false);
                            handArray.add(addHand);
                        }
                    }
                }
            }
        }
    }

    private void move(Hand hand, int group) {
        int before = hand.getBefore();
        int after = hand.getAfter();
        if (this.board.isAnemy(group, after)) { 
            hand.setCatchPieceType(this.board.getPiece(after).getPieceType());
            this.board.addHasPiece(group, this.board.getPiece(after).getPieceType());
        } else {
            hand.setCatchPieceType(0);
        }
        hand.setPieceNum(this.board.getPiece(before).getPieceType());
        if (after%5 - 1 - 2*this.board.getTurn() == this.board.getTurn() && hand.getPieceNum() == HIYO) {
            hand.setPromoted(true);
            this.board.getPiece(before).promoted(); 
        } else {
            hand.setPromoted(false);
        }
        this.board.movePiece(before, after);
    }

    private void drop(Hand hand, int group) {
        this.board.setPiece(group, hand.getPieceNum(), hand.getAfter());
        this.board.subHasPiece(group, hand.getPieceNum());
    }

    private boolean isWin(int turn) {
        if (this.board.isHasPiece(turn, RION)) return true;
        else if (isTry(turn)) return true;
        return false;
    }
    private boolean isTry(int turn) {
        int y = 1 + 3*turn;
        for (int x = 5; x <= 15; x += 5) {
            if (this.board.isPiece(x+y) && this.board.getPiece(x+y).getPieceType() == RION && this.board.getPiece(x+y).getGroup() == turn) {
                if (!isAnemyControl(turn, x+y)) return true;
            }
        }
        return false;
    }
    private boolean isAnemyControl(int turn, int posi) {
        for (int y = -1; y <= 1; y++) {
            for (int x = -5; x <= 5; x += 5) {
                if (this.board.isAnemy(turn, posi+y+x) && this.board.getPiece(posi+y+x).isControl(posi+y+x, posi)) return true;
            }
        }
        return false;
    }

    private void selectHand() {
        int max = 0;
        for (int i = 0; i < winN.size(); i++) {
            if (winN.get(i) > max) {
                max = winN.get(i);
                this.hand = this.handArray.get(i);
            }
        }
    }

}
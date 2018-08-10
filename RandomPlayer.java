import java.util.List;
import java.util.ArrayList;
public class RandomPlayer extends Player implements PieceID {    
    private Board board;
    private List<Hand> handArray;

    RandomPlayer() {}
    RandomPlayer(int n, Board board) {
        super(n);
        this.board = board;
        this.handArray = new ArrayList<Hand>();
    }
    
    public void turn() {
        this.handArray.clear();
        addMove();
        addDrop();
        int rand = new java.util.Random().nextInt(this.handArray.size());
        this.hand = this.handArray.get(rand);
    }

    private void addMove() {
        for (int x = 5; x <= 15; x += 5) {
            for (int y = 1; y <= 4; y++) {
                if (this.board.isAlly(this.group, x+y)) {
                    for (int tx = -5; tx <= 5; tx += 5) {
                        for (int ty = -1; ty <= 1; ty++) {
                            if (this.board.getPiece(x+y).isControl(x+y, x+y+tx+ty) && !isOut(x+y+tx+ty) && !this.board.isAlly(this.group, x+y+tx+ty)) {
                                Hand addHand = new Hand();
                                addHand.setMove(0);
                                addHand.setBefore(x+y);
                                addHand.setAfter(x+y+tx+ty);
                                this.handArray.add(addHand);
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isOut(int posi) {
        if (posi < 0 || posi > 25 || this.board.getPosi(posi) == -1) return true;
        return false;
    }

    private void addDrop() {
        for (int n = KIRIN; n <= HIYO; n++) {
            if (this.board.isHasPiece(this.group, n)) {
                for (int x = 5; x <= 15; x += 5) {
                    for (int y = 1; y <= 4; y++) {
                        if (!this.board.isPiece(x+y)) {
                            Hand addHand = new Hand(1, 0, x+y, n, 0, false);
                            this.handArray.add(addHand);
                        }
                    }
                }
            }
        }
    }

}
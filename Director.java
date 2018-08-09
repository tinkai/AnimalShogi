import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
public class Director implements PieceID{
    private Player p1;
    private Player p2;
    private Board board;

    Director() {
        this.p1 = new HumanPlayer(0);
        this.p2 = new HumanPlayer(1);
        this.board = new Board();
        System.out.println("Player1(0)(1)I");
        Scanner scanner = new Scanner(System.in);
        int turn = scanner.nextInt();
        if (turn == 1) this.board.changeTurn();
    }

    public void game() {
        System.out.println("Q[Jn");
        System.out.println("");
        this.board.showTurnN();
        this.board.showTurn();
        this.board.showPosi();
        this.board.showHasPiece();
        System.out.println();
        while(true) {
            this.board.showTurnN();
            this.board.showTurn();
            if (this.board.getTurn() == 0) playerTurn(this.p1);
            else if (this.board.getTurn() == 1) playerTurn(this.p2);
            this.board.showPosi();
            this.board.showHasPiece();
            System.out.println();
            if (isWin(this.board.getTurn())) break;
            this.board.changeTurn();        
            this.board.addTurnN();
        }
        showWinner();
    }

    private void playerTurn(Player p) {
        while(true) {
            p.turn();   // 
            Hand hand = p.getHand();
            int before = hand.getBefore();
            int after = hand.getAfter();
            if (before == -1) {
                System.out.println("XyH");
                continue;
            }
            /*if (p.getHand().getMove().equals("show")) {
                this.board.showPosi();
                this.board.showHasPiece();
                this.board.showTurn();
                continue;
            }*/
            if (!decSasite(p)) continue;
            if (hand.getMove()) { // 
                if (this.board.isAnemy(p.getGroup(), after)) { // G
                    hand.setCatchPieceType(this.board.getPiece(after).getPieceType());
                    this.board.addHasPiece(p.getGroup(), this.board.getPiece(after).getPieceType());
                } else {
                    hand.setCatchPieceType(0);
                }
                hand.setPieceNum(this.board.getPiece(before).getPieceType());
                if (after%5 - 1 - 2*this.board.getTurn() == this.board.getTurn() && hand.getPieceNum() == HIYO) {   // AqR
                    hand.setPromoted(true);
                    this.board.getPiece(after).promoted(); 
                } else {
                    hand.setPromoted(false);
                }
                this.board.movePiece(before, after);
                break;
            } else if (!hand.getMove()) { // 
                this.board.setPiece(p.getGroup(), hand.getPieceNum(), after);
                this.board.subHasPiece(p.getGroup(), hand.getPieceNum());
                break;
            }
        }
    }

    private boolean decSasite(Player p) {
        if (isOut(p)) {
            System.out.println("OwH");
            return false; 
        }
        Hand hand = p.getHand();
        int before = hand.getBefore();
        int after = hand.getAfter();
        if (hand.getMove()) {
            if (!this.board.isAlly(p.getGroup(), before)) {   // ����
                System.out.println("H");
                return false;
            } else if (!this.board.getPiece(before).isControl(before, after)) {  // 
                System.out.println("I");
                return false;
            } else if (this.board.isAlly(p.getGroup(), after)) { // 
                System.out.println("");
                return false;
            }
        } else if (!hand.getMove()) {
            if (hand.getPieceNum() <= 0 || hand.getPieceNum() > HIYO) {
                System.out.println("H");
                return false;
            } else if (!this.board.isHasPiece(p.getGroup(), hand.getPieceNum())) {
                System.out.println("H");
                return false;
            } else if (this.board.isPiece(after)) {
                System.out.println("u��");
                return false;
            }
        }
        return true;
    }
    private boolean isOut(Player p) {
        int after = p.getHand().getAfter();
        int before = p.getHand().getBefore();
        if (after < 0 || after > 25 || this.board.getPosi(after) == -1) return true;
        if (p.getHand().getMove()) {
            if (before < 0 || before > 25 || this.board.getPosi(before) == -1) return true;
        } 
        return false;
    }

    // // 
    private boolean isWin(int turn) {
        if (this.board.isHasPiece(turn, RION)) {
            System.out.println("CII");
            return true;
        } else if (isTry(turn)) {
            System.out.println("gCI");
            return true;
        }
        return false;
    }
    // gC
    private boolean isTry(int turn) {
        int y = 1 + 3*turn;
        for (int x = 5; x <= 15; x += 5) {
            if (this.board.isPiece(x+y) && this.board.getPiece(x+y).getPieceType() == RION && this.board.getPiece(x+y).getGroup() == turn) {
                if (!isAnemyControl(turn, x+y)) return true;
            }
        }
        return false;
    }
    // GR}
    private boolean isAnemyControl(int turn, int posi) {
        for (int y = -1; y <= 1; y++) {
            for (int x = -5; x <= 5; x += 5) {
                if (this.board.isAnemy(turn, posi+y+x) && this.board.getPiece(posi+y+x).isControl(posi+y+x, posi)) return true;
            }
        }
        return false;
    }
    private void showWinner() {
        System.out.println("Player" + (this.board.getTurn()+1) + "!!!");
    }
    
}
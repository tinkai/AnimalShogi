import java.util.List;
import java.util.ArrayList;
public class History {
    private List<Hand> history;
    private Board board;

    History() {}
    History(Board board) {
        this.history = new ArrayList<Hand>();
        this.board = board;
    }

    public List getHistory() {
        return this.history;
    }
    public void add(Hand hand) {
        this.history.add(new Hand(hand));
    }
    public void undo() {
        Hand hand = this.history.get(this.history.size()-1);
        this.history.remove(this.history.size()-1);
        this.board.subTurnN();
        this.board.changeTurn();
        if (hand.getMove() == 0) {
            if (hand.getPromoted()) this.board.getPiece(hand.getAfter()).demoted();
            this.board.movePiece(hand.getAfter(), hand.getBefore());
            if (hand.getCatchPieceType() != 0) {
                this.board.subHasPiece(this.board.getTurn(), hand.getCatchPieceType());
                this.board.changeTurn();
                this.board.setPiece(this.board.getTurn(), hand.getCatchPieceType(), hand.getAfter());
                this.board.changeTurn();
            }
        } else if (hand.getMove() == 1) {
            this.board.removePiece(hand.getAfter());
            this.board.addHasPiece(this.board.getTurn(), hand.getPieceNum());
        }
    }


     // 確認用
    public void show() {
        for (int i = 0; i < this.history.size(); i++) {
            System.out.println(this.history.get(i).getBefore());
            System.out.println(this.history.get(i).getAfter());
            System.out.println(this.history.get(i).getPieceNum());
        }
    }

}
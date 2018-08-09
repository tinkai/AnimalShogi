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
        System.out.println("Player1の先手(0)後手(1)を選んでください");
        Scanner scanner = new Scanner(System.in);
        int turn = scanner.nextInt();
        if (turn == 1) this.board.changeTurn();
    }

    public void game() {
        System.out.println("ゲームを開始します");
        System.out.println("初期盤面");
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
            p.turn();   // 操作の入力
            if (!p.getHand().getMove().equals("move") && !p.getHand().getMove().equals("drop") && !p.getHand().getMove().equals("show") && !p.getHand().getMove().equals("undo")) {
                System.out.println("スペルあっとる？");
                continue;
            }
            if (p.getHand().getMove().equals("show")) {
                this.board.showPosi();
                this.board.showHasPiece();
                this.board.showTurn();
                continue;
            }
            
            if (!decSasite(p)) continue;
            if (p.getHand().getMove().equals("move")) { // 移動
                if (this.board.isAnemy(p.getGroup(), p.getHand().getAfter())) { // 移動さきに敵がいたら持ち駒にする
                    this.board.addHasPiece(p.getGroup(), this.board.getPiece(p.getHand().getAfter()).getPieceType());
                }
                this.board.movePiece(p.getHand().getBefore(), p.getHand().getAfter());
                break;
            } else if (p.getHand().getMove().equals("drop")) { // 打ち込み
                this.board.setPiece(p.getGroup(), p.getHand().getPieceNum(), p.getHand().getAfter());
                this.board.subHasPiece(p.getGroup(), p.getHand().getPieceNum());
                break;
            }
        }
    }

    private boolean decSasite(Player p) {
        if (isOut(p)) { // 盤外か
            System.out.println("盤外を指定していませんか？");
            return false; 
        }
        if (p.getHand().getMove().equals("move")) {
            if (!this.board.isAlly(p.getGroup(), p.getHand().getBefore())) {   // 移動駒が自分の駒か
                System.out.println("自分の駒？");
                return false;
            } else if (!this.board.getPiece(p.getHand().getBefore()).isControl(p.getHand().getBefore(), p.getHand().getAfter())) {  // 利きがあっているか
                System.out.println("移動できないですよ！");
                return false;
            } else if (this.board.isAlly(p.getGroup(), p.getHand().getAfter())) { // 味方がいるか
                System.out.println("移動先に味方がいますよ");
                return false;
            }
        } else if (p.getHand().getMove().equals("drop")) {
            if (p.getHand().getPieceNum() <= 0 || p.getHand().getPieceNum() > HIYO) {
                System.out.println("番号を間違えてませんか？");
                return false;
            } else if (!this.board.isHasPiece(p.getGroup(), p.getHand().getPieceNum())) {
                System.out.println("持ち駒に持っていますか？");
                return false;
            } else if (this.board.isPiece(p.getHand().getAfter())) {
                System.out.println("置く場所に駒がありますよ");
                return false;
            }
        }
        return true;
    }
    private boolean isOut(Player p) {
        int after = p.getHand().getAfter();
        int before = p.getHand().getBefore();

        if (after < 0 || after > 25 || this.board.getPosi(after) == -1) return true;
        if (p.getHand().getMove().equals("move")) {
            if (before < 0 || before > 25 || this.board.getPosi(before) == -1) return true;
        } 
        return false;
    }

    // 勝利判定
    private boolean isWin(int turn) {
        if (this.board.isHasPiece(turn, RION)) {
            System.out.println("ライオンをとりました！");
            return true;
        } else if (isTry(turn)) {
            System.out.println("トライしました！");
            return true;
        }
        return false;
    }
    // トライ判定
    private boolean isTry(int turn) {
        int y = 1 + 3*turn;
        for (int x = 5; x <= 15; x += 5) {
            if (this.board.isPiece(x+y) && this.board.getPiece(x+y).getPieceType() == RION && this.board.getPiece(x+y).getGroup() == turn) {
                if (!isAnemyControl(turn, x+y)) return true;
            }
        }
        return false;
    }
    // 敵のコマが利いているか
    private boolean isAnemyControl(int turn, int posi) {
        for (int y = -1; y <= 1; y++) {
            for (int x = -5; x <= 5; x += 5) {
                if (this.board.isAnemy(turn, posi+y+x) && this.board.getPiece(posi+y+x).isControl(posi+y+x, posi)) return true;
            }
        }
        return false;
    }
    private void showWinner() {
        System.out.println("勝ったのはPlayer" + (this.board.getTurn()+1) + "!!!");
    }
}
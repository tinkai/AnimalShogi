import java.util.Scanner;
public class Director implements PieceID{
    private Player p1;
    private Player p2;
    private Board board;
    private History history;

    Director() {}
    Director(int p1, int p2, int turn) {
        this.board = new Board();
        this.p1 = createNewPlayer(0, p1);
        this.p2 = createNewPlayer(1, p2);
        if (turn == 1) this.board.changeTurn();
        this.history = new History(this.board);
    }
    
    private Player createNewPlayer(int group, int player) {
        Player p;
        switch (player) {
        case 0:
            p = new HumanPlayer(group); break;
        case 1:
            p = new RandomPlayer(group, this.board); break;
        case 2:
            p = new MonteCarloPlayer(group, this.board); break;
        default:
            System.out.println("値がおかしいです　Humanにしますね");
            p = new HumanPlayer(group);
        }
        return p;
    }

    public int getWinner() {
        return this.board.getTurn();
    }
    
    public void noShowGame() {
        while(true) {
            if (this.board.getTurn() == 0) playerTurn(this.p1);
            else if (this.board.getTurn() == 1) playerTurn(this.p2);
            if (isWinNoShow(this.board.getTurn())) break;    
            this.board.changeTurn();        
            this.board.addTurnN();        
        }
        //showWinner();
        //this.board.showTurnN();
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
            if (this.board.getTurn() == 0) {
                playerTurn(this.p1);
                if (this.p1.getHand().getMove() == 3 || this.p1.getHand().getMove() == 4) continue;
            } else if (this.board.getTurn() == 1) {
                playerTurn(this.p2);
                if (this.p2.getHand().getMove() == 3 || this.p2.getHand().getMove() == 4) continue;
            }
            this.board.showPosi();
            this.board.showHasPiece();
            //this.history.show();
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
            Hand hand = p.getHand();
            int before = hand.getBefore();
            int after = hand.getAfter();
            if (hand.getMove() == -1) {
                System.out.println("スペルあっとる？");
                continue;
            }
            if (hand.getMove() == 2) {
                this.board.showPosi();
                this.board.showHasPiece();
                this.board.showTurnN();
                this.board.showTurn();
                continue;
            }
            if (hand.getMove() == 3) {
                this.history.undo();
                this.board.showPosi();
                this.board.showHasPiece();
                return;
            }
            if (hand.getMove() == 4) {
                this.history.undo();
                this.history.undo();
                this.board.showPosi();
                this.board.showHasPiece();
                return;
            }
            if (!decSasite(p)) continue;
            if (hand.getMove() == 0) {
                if (this.board.isAnemy(p.getGroup(), after)) { 
                    hand.setCatchPieceType(this.board.getPiece(after).getPieceType());
                    this.board.addHasPiece(p.getGroup(), this.board.getPiece(after).getPieceType());
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
                break;
            } else if (hand.getMove() == 1) {
                this.board.setPiece(p.getGroup(), hand.getPieceNum(), after);
                this.board.subHasPiece(p.getGroup(), hand.getPieceNum());
                break;
            }
        }
        this.history.add(p.getHand());
    }

    private boolean decSasite(Player p) {
        if (isOut(p)) {
            System.out.println("盤外を指定していませんか？");
            return false; 
        }
        Hand hand = p.getHand();
        int before = hand.getBefore();
        int after = hand.getAfter();
        if (hand.getMove() == 0) {
            if (!this.board.isAlly(p.getGroup(), before)) {
                System.out.println("自分の駒？");
                return false;
            } else if (!this.board.getPiece(before).isControl(before, after)) { 
                System.out.println("移動できないですよ！");
                return false;
            } else if (this.board.isAlly(p.getGroup(), after)) { 
                System.out.println("移動先に味方がいますよ");
                return false;
            }
        } else if (hand.getMove() == 0) {
            if (hand.getPieceNum() <= 0 || hand.getPieceNum() > HIYO) {
                System.out.println("番号を間違えてませんか？");
                return false;
            } else if (!this.board.isHasPiece(p.getGroup(), hand.getPieceNum())) {
                System.out.println("持ち駒に持っていますか？");
                return false;
            } else if (this.board.isPiece(after)) {
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
        if (p.getHand().getMove() == 0) {
            if (before < 0 || before > 25 || this.board.getPosi(before) == -1) return true;
        } 
        return false;
    }

    private boolean isWinNoShow(int turn) {
        if (this.board.isHasPiece(turn, RION)) return true;
        else if (isTry(turn)) return true;
        return false;
    }
    private boolean isWin(int turn) {
        if (this.board.isHasPiece(turn, RION)) {
            System.out.println("ライオンをとりました！");
            return true;
        } else if (isTry(turn)) {
            System.out.println("トライ！");
            return true;
        }
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
    private void showWinner() {
        System.out.println("勝ったのはPlayer" + (this.board.getTurn()+1) + "!!!");
    }
    
}
import java.util.Scanner;
public class HumanPlayer extends Player {
    HumanPlayer(){}
    HumanPlayer(int n) {
        super(n);
    }

    public void turn() {
        System.out.println("指し手を入力してください 移動(move), 打ち込み(drop), 盤面表示(show), 1手戻る(undo), 2手戻る(undo2)");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.next();
        switch (move) {
        case "move" :
            move(); break;
        case "drop" :
            drop(); break;
        case "show" :
            this.hand.setMove(2); break;
        case "undo" :
            this.hand.setMove(3); break;
        case "undo2" :
            this.hand.setMove(4); break;
        default :
            this.hand.setMove(-1);
        }
    }
    private void move() {
        this.hand.setMove(0);
        System.out.println("移動前と移動後の番地を指定してください 例：2 3 2 2");
        Scanner scanner = new Scanner(System.in);
        int forX = scanner.nextInt();
        int forY = scanner.nextInt();
        int toX = scanner.nextInt();
        int toY = scanner.nextInt();
        this.hand.setBefore(changeOneDim(forX, forY));
        this.hand.setAfter(changeOneDim(toX, toY));
    }
    private void drop() {
        this.hand.setMove(1);
        this.hand.setBefore(0);
        this.hand.setCatchPieceType(0);
        this.hand.setPromoted(false);
        System.out.println("打ち込む駒と番地を指定してください 例：3 2 3");
        Scanner scanner = new Scanner(System.in);
        int pieceN = scanner.nextInt();
        int toX = scanner.nextInt();
        int toY = scanner.nextInt();
        this.hand.setPieceNum(pieceN);
        this.hand.setAfter(changeOneDim(toX, toY));
    }
}
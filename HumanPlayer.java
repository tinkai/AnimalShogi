import java.util.Scanner;
public class HumanPlayer extends Player {
    HumanPlayer(){}
    HumanPlayer(int n) {
        super(n);
    }

    public void turn() {
        System.out.println("w (move), (drop), (show), (undo)");
        Scanner scanner = new Scanner(System.in);
        String move = scanner.next();
        if (move.equals("move")) move();
        else if (move.equals("drop")) drop();
        else this.hand.setBefore(-1);;
    }
    private void move() {
        this.hand.setMove(true);
        System.out.println("Onw F2 3 2 2");
        Scanner scanner = new Scanner(System.in);
        int forX = scanner.nextInt();
        int forY = scanner.nextInt();
        int toX = scanner.nextInt();
        int toY = scanner.nextInt();
        this.hand.setBefore(changeOneDim(forX, forY));
        this.hand.setAfter(changeOneDim(toX, toY));
    }
    private void drop() {
        this.hand.setMove(false);
        this.hand.setBefore(0);
        this.hand.setCatchPieceType(0);
        this.hand.setPromoted(false);
        System.out.println("nw F3 2 3");
        Scanner scanner = new Scanner(System.in);
        int pieceN = scanner.nextInt();
        int toX = scanner.nextInt();
        int toY = scanner.nextInt();
        this.hand.setPieceNum(pieceN);
        this.hand.setAfter(changeOneDim(toX, toY));
    }
}
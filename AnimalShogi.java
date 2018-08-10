import java.util.Scanner;

public class AnimalShogi {
    public static void main(String[] args) {
        System.out.println("どうぶつしょうぎ");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Player1を選んでください  0.Human, 1.Random, 2.MonteCarlo");
        int p1 = scanner.nextInt();
        System.out.println("Player2を選んでください  0.Human, 1.Random, 2.MonteCarlo");
        int p2 = scanner.nextInt();
        System.out.println("Player1の先手(0)後手(1)を選んでください");
        int turn = scanner.nextInt();
        int n;
        boolean show = true;
        if ((p1 == 1 || p1 == 2) && (p2 == 1 || p2 == 2)) {
            System.out.println("何回戦いますか?");
            n = scanner.nextInt();
            System.out.println("盤面表示しますか? y n");
            String b = scanner.next();
            if (b.equals("n")) show = false;
        } else {
            n = 1;
        }
        int p1Vic = 0;
        int p2Vic = 0;
        while (n-- > 0) {
            Director director = new Director(p1, p2, turn);
            if (show) director.game();
            else director.noShowGame();
            if (director.getWinner() == 0) p1Vic++;
            else if (director.getWinner() == 1) p2Vic++;
        }
        if ((p1 == 1 || p1 == 2) && (p2 == 1 || p2 == 2)) {
            System.out.println("Player1の勝利回数" + p1Vic);
            System.out.println("Player2の勝利回数" + p2Vic);
        }
        System.exit(0);
    }
    
}
public class Board implements PieceID, PieceLongName {
    private int posi[];
    private Piece piecePosi[];
    private int hasPiece[][];
    private int turn;
    private int turnN;

    Board() {
        this.posi = new int[26];
        this.piecePosi = new Piece[26];
        defaultPosi();
        this.hasPiece = new int[2][5];
        this.turn = 0;
        this.turnN = 1;
    }

    private void defaultPosi() {
        for (int i = 0; i < this.posi.length; i++) {
            if (i < 5 || i > 20 || i % 5 == 0) this.posi[i] = OUT;
        }
        this.piecePosi[14] = new Piece(RION, 0);
        this.posi[14] = RION;
        this.piecePosi[9] = new Piece(KIRIN, 0);
        this.posi[9] = KIRIN;
        this.piecePosi[19] = new Piece(ZOU, 0);
        this.posi[19] = ZOU;
        this.piecePosi[13] = new Piece(HIYO, 0);
        this.posi[13] = HIYO;
        this.piecePosi[11] = new Piece(ERION, 1);
        this.posi[11] = ERION;
        this.piecePosi[16] = new Piece(EKIRIN, 1);
        this.posi[16] = EKIRIN;
        this.piecePosi[6] = new Piece(EZOU, 1);
        this.posi[6] = EZOU;
        this.piecePosi[12] = new Piece(EHIYO, 1);
        this.posi[12] = EHIYO;
    }

    public int getPosi(int posi) {
        return this.posi[posi];
    }

    // 盤面コマ関係
    public Piece getPiece(int posi) {
        return this.piecePosi[posi];
    }
    public void setPiece(int group, int n, int posi) {
        this.posi[posi] = n + group*ANEMY;
        this.piecePosi[posi] = new Piece(n + group*ANEMY, group);
    }
    public void movePiece(int before, int after) {
        this.posi[after] = this.posi[before];
        this.piecePosi[after] = this.piecePosi[before];
        if (after%5 - 1 - 2*this.turn == this.turn && this.piecePosi[after].getPieceType() == HIYO) this.piecePosi[after].promoted(); // もし、ヒヨコなら成る
        this.posi[before] = EMPTY;
        this.piecePosi[before] = null;
    }
    public void showPosi() {
        System.out.println("   3   2   1");
        for (int i = 6; i < 10; i++) {
            System.out.println(" -------------");
            System.out.print(" ");
            for (int j = 10; j >= 0; j -= 5) {
                System.out.print("|");
                if (this.posi[i+j] <= EMPTY ) System.out.print("   ");
                else System.out.print(this.piecePosi[i+j].getName());
            }
            System.out.println("|" + (i-5));
        }
        System.out.println(" -------------");
    }

    // 盤内のコマ判定 
    public boolean isPiece(int posi) {
        if (this.posi[posi] > EMPTY) return true;
        return false;
    }
    public boolean isAnemy(int group, int posi) {
        if (!isPiece(posi)) return false;
        if (this.piecePosi[posi].getGroup() != group) return true;
        return false;
    }
    public boolean isAlly(int group, int posi) {
        if (!isPiece(posi)) return false;
        if (this.piecePosi[posi].getGroup() == group) return true;
        return false;
    }

    // 持ち駒関係
    public boolean isHasPiece(int group, int n) {
        if (this.hasPiece[group][n] > 0) return true;
        return false;
    }
    public void addHasPiece(int group, int n) {
        if (n == NIWA) n = HIYO;
        this.hasPiece[group][n]++;
    }
    public void subHasPiece(int group, int n) {
        this.hasPiece[group][n]--;
    }
    public void showHasPiece() {
        showHasPiece(0);
        showHasPiece(1);
    }
    private void showHasPiece(int group) {
        System.out.println("Player" + (group+1) + "の持ち駒");
        for (int i = 1; i <= HIYO; i++) {
            if (this.hasPiece[group][i] > 0) {
                switch (i) {
                case RION:
                    System.out.print(i + "." + RLNAME + this.hasPiece[group][i] + "つ ");
                    break;
                case KIRIN:
                    System.out.print(i + "." + KLNAME + this.hasPiece[group][i] + "つ ");
                    break;
                case ZOU:
                    System.out.print(i + "." + ZLNAME + this.hasPiece[group][i] + "つ ");
                    break;
                case HIYO:
                    System.out.print(i + "." + HLNAME + this.hasPiece[group][i] + "つ ");
                    break;
                }
            }
        }
        System.out.println();
    }

    // ターン関係
    public int getTurn() {
        return this.turn;
    }
    public void changeTurn() {
        if (this.turn == 0) this.turn = 1;
        else this.turn = 0;
    }
    public void showTurn() {
        if (this.turn == 0) System.out.println("Player1のターン");
        else System.out.println("Player2のターン");
    }

    // ターン数関係
    public void addTurnN() {
        this.turnN++;
    }
    public void showTurnN() {
        System.out.println("ターン数 " + this.turnN);
    }
}
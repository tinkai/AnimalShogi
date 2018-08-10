public class Hand {
    private int move;   // 0からmove, drop, show, undo, undo2
    private int before; 
    private int after;
    private int pieceNum;       
    private int catchPieceType; 
    private boolean promoted;
    
    Hand(){}
    Hand(Hand hand) {
        this.move = hand.getMove();
        this.before = hand.getBefore();
        this.after = hand.getAfter();
        this.pieceNum = hand.getPieceNum();
        this.catchPieceType = hand.getCatchPieceType();
        this.promoted = hand.getPromoted();
    }
    Hand(int move, int before, int after, int pieceNum, int catchPieceType, boolean promoted) {
        this.move = move;
        this.before = before;
        this.after = after;
        this.pieceNum = pieceNum;
        this.catchPieceType = catchPieceType;
        this.promoted = promoted;
    }

    public int getMove() {
        return this.move;
    }
    public void setMove(int move) {
        this.move = move;
    }
    public int getBefore() {
        return this.before;
    }
    public void setBefore(int posi) {
        this.before = posi;
    }
    public int getAfter() {
        return this.after;
    }
    public void setAfter(int posi) {
        this.after = posi;
    }
    public int getPieceNum() {
        return this.pieceNum;
    }
    public void setPieceNum(int piece) {
        this.pieceNum = piece;
    }
    public int getCatchPieceType() {
        return this.catchPieceType;
    }
    public void setCatchPieceType(int n) {
        this.catchPieceType = n;
    }
    public boolean getPromoted() {
        return this.promoted;
    }
    public void setPromoted(boolean b) {
        this.promoted = b;
    }

    public void handInfo() {
        System.out.print(this.move + " " + this.before + " " + this.after + " " + this.pieceNum + " " + this.catchPieceType);
        if (this.promoted) System.out.println(" y");
        else System.out.println(" n");
    }
}
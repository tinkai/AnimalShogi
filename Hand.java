public class Hand {
    private boolean move;
    private int before; // 0 Xy~X-1
    private int after;
    private int pieceNum;       // 
    private int catchPieceType; //  0
    private boolean promoted;
    
    Hand(){}

    public boolean getMove() {
        return this.move;
    }
    public void setMove(boolean b) {
        this.promoted = b;
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
}
public class Hand {
    private String move;
    private int before;
    private int after;
    private int pieceNum;
    

    Hand(){}

    public String getMove() {
        return this.move;
    }
    public void setMove(String move) {
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
}
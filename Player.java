public class Player {
    protected Hand hand;
    protected int group;

    Player() {}
    Player(int n) {
        this.hand = new Hand();
        this.group = n;
    }

    public Hand getHand() {
        return this.hand;
    }
    public int getGroup() {
        return this.group;
    }

    public void turn() {}
    
    // 画面表示されている番地からデータ上の番地に変換
    protected int changeOneDim(int x, int y) {
        return 5*x + y;
    }
}
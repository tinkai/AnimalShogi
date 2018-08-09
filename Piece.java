public class Piece implements PieceID, PieceName, PieceControl {
    private int id;
    private String name;   
    private int control[];  // 利き
    private int group;  // 先手後手どちらの持ち駒か

    Piece() {
    }

    Piece(int n, int group) {
        this.id = n;
        setPiece(n);
        this.group = group;
    }

    public void setPiece(int n) {
        switch (n) {
        case RION:
            this.name = RNAME;
            this.control = RCON;
            break;
        case KIRIN:
            this.name = KNAME;
            this.control = KCON;
            break;
        case ZOU:
            this.name = ZNAME;
            this.control = ZCON;
            break;
        case HIYO:
            this.name = HNAME;
            this.control = HCON;
            break;
        case NIWA:
            this.name = NNAME;
            this.control = NCON;
            break;
        case ERION:
            this.name = ERNAME;
            this.control = ERCON;
            break;
        case EKIRIN:
            this.name = EKNAME;
            this.control = EKCON;
            break;
        case EZOU:
            this.name = EZNAME;
            this.control = EZCON;
            break;
        case EHIYO:
            this.name = EHNAME;
            this.control = EHCON;
            break;
        case ENIWA:
            this.name = ENNAME;
            this.control = ENCON;
            break;
        }
    }
    public int getPieceType() {
        return this.id - this.group*ANEMY;
    }
    public String getName() {
        return this.name;
    }
    public boolean isControl(int before, int after) {
        for (int i = 0; i < this.control.length; i++) {
            if (this.control[i] == after-before) return true;
        }
        return false;
    }
    public int getGroup() {
        return this.group;
    }

    // コマの変化(ヒヨコ・ニワトリ用)
    public void promoted() {
        this.id++;
        if (this.id == NIWA) {
            this.name = NNAME;
            this.control = NCON;
        } else if (this.id == ENIWA) {
            this.name = ENNAME;
            this.control = ENCON;
        }
    }
    public void demoted() {
        this.id--;
        if (this.id == HIYO) {
            this.name = HNAME;
            this.control = HCON;
        } else if (this.id == EHIYO) {
            this.name = EHNAME;
            this.control = EHCON;
        }
    }
    
}
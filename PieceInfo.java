// コマの番号
interface PieceID {
        int OUT = -1;
        int EMPTY = 0;
        int RION = 1;
        int KIRIN = 2;
        int ZOU = 3;
        int HIYO = 4;
        int NIWA = 5;
        int ANEMY = 5;
        int ERION = RION + ANEMY;
        int EKIRIN = KIRIN + ANEMY;
        int EZOU = ZOU + ANEMY;
        int EHIYO = HIYO + ANEMY;
        int ENIWA = NIWA + ANEMY;
    }
    // コマの名前
    interface PieceName {
        String RNAME = "△ラ";
        String KNAME = "△キ";
        String ZNAME = "△ゾ";
        String HNAME = "△ヒ";
        String NNAME = "△ニ";
        String ERNAME = "▼ラ";
        String EKNAME = "▼キ";
        String EZNAME = "▼ゾ";
        String EHNAME = "▼ヒ";
        String ENNAME = "▼ニ";
    }
    interface PieceLongName {
        String RLNAME = "ライオン";
        String KLNAME = "キリン";
        String ZLNAME = "ゾウ";
        String HLNAME = "ヒヨコ";
        String NLNAME = "ニワトリ";
    }
    // コマの利き
    interface PieceControl {
        int RCON[] = {-6, -5, -4, -1, 0, 1, 4, 5, 6};
        int KCON[] = {0, -5, 0, -1, 0, 1, 0, 5, 0};
        int ZCON[] = {-6, 0, -4, 0, 0, 0, 4, 0, 6};
        int HCON[] = {0, 0, 0, -1, 0, 0, 0, 0, 0};
        int NCON[] = {-6, -5, 0, -1, 0, 1, 4, 5, 0};
        int ERCON[] = {-6, -5, -4, -1, 0, 1, 4, 5, 6};
        int EKCON[] = {0, -5, 0, -1, 0, 1, 0, 5, 0};
        int EZCON[] = {-6, 0, -4, 0, 0, 0, 4, 0, 6};
        int EHCON[] = {0, 0, 0, 0, 0, 1, 0, 0, 0};
        int ENCON[] = {0, -5, -4, -1, 0, 1, 0, 5, 6};
    }
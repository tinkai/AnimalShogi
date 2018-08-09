// �R�}�̔ԍ�
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
    // �R�}�̖��O
    interface PieceName {
        String RNAME = "����";
        String KNAME = "���L";
        String ZNAME = "���]";
        String HNAME = "���q";
        String NNAME = "���j";
        String ERNAME = "����";
        String EKNAME = "���L";
        String EZNAME = "���]";
        String EHNAME = "���q";
        String ENNAME = "���j";
    }
    interface PieceLongName {
        String RLNAME = "���C�I��";
        String KLNAME = "�L����";
        String ZLNAME = "�]�E";
        String HLNAME = "�q���R";
        String NLNAME = "�j���g��";
    }
    // �R�}�̗���
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
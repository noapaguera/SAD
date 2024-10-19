public class KEY {
    // ASCII code
    public static final int CR_ASCII = 13; // Carriage Return
    public static final int ESC_ASCII = 27; // ESC
    public static final int INSERT_ASCII = 50; // 2 ^[[2~
    public static final int DELETE_ASCII = 51; // 3 ^[[3~
    public static final int UP_ASCII = 65; // A ^[[A
    public static final int DOWN_ASCII = 66; // B ^[[B
    public static final int RIGHT_ASCII = 67; // C ^[[C
    public static final int LEFT_ASCII = 68; // D ^[[D
    public static final int FIN_ASCII = 70; // F ^[[F
    public static final int HOME_ASCII = 72; // H ^[[H
    public static final int BRACKET_ASCII = 91; // "["
    public static final int TILDE_ASCII = 126; // "~"
    public static final int BACKSPACE_ASCII = 127; // "\b"

    // ANSI escape code
    public static final String INS_ANSI = "\033[2~";
    public static final String DEL_ANSI = "\033[3~";
    public static final String UP_ANSI = "\033[A";
    public static final String DOWN_ANSI = "\033[B";
    public static final String RIGHT_ANSI = "\033[C";
    public static final String LEFT_ANSI = "\033[D";
    public static final String END_ANSI = "\033[F";
    public static final String HOME_ANSI = "\033[H";

    public static final int INSERT = -50;
    public static final int DELETE = -51;
    public static final int UP = -65;
    public static final int DOWN = -66;
    public static final int RIGHT = -67;
    public static final int LEFT = -68;
    public static final int FIN = -70;
    public static final int HOME = -72;
    public static final int BACKSPACE = -127;
}
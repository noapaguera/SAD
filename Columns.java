import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Columns {
    public static void main(String[] args) {
        setRaw();
        int c = 0;
        try {
            // report size of text area in chars: CSI 18 t
            // should return ESC [ 8 ; rows ; cols t
            System.out.println("\033[18t");
            // 1st alternative
            /* String[] getColumn = new String[] { "sh", "-c", "tput cols < /dev/tty" };
            try {
                Process p = new ProcessBuilder(getColumn).start();
                BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
                String maxColStr = reader.readLine();
                reader.close();
                c = Integer.parseInt(maxColStr.trim());
            } catch (Exception e) {
            } */
           // 2nd alternative
           Scanner sc = new Scanner(System.in);
           sc.skip("\033\\[8;\\d+)t");
           c = Integer.parseInt(sc.match().group(1));

        } finally {
            unsetRaw();
        }
        System.out.println("Columns: " + c);
    }
}

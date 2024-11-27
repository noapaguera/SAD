package EditableBufferedReaderMVC;

import java.io.*;

class TestReadLine {
    public static void main(String[] args) {
        BufferedReader in = new EditableBufferedReader(new InputStreamReader(System.in));
        try {
            System.out.println("\nline is: " + in.readLine());
        } catch (IOException e) { e.printStackTrace(); }
    }
}

/* ARREGLAR:
ok tecla Supr (delete)
tecla Fin
la vista despres de fer Enter
text dsps de Enter
*/
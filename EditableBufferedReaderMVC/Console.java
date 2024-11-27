package EditableBufferedReaderMVC;

import java.util.Observable;
import java.util.Observer;

public class Console implements Observer{
    private Line l;
    public Console (Line line) {
        this.l = line;
    }

    public void update(Observable Obs, Object arg) {
        //System.out.println(arg);
        if (Obs == l) {
            System.out.print((String) arg);
            System.out.flush();
        }
    }
}

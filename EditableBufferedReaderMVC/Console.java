package EditableBufferedReaderMVC;

import java.util.Observable;
import java.util.Observer;

public class Console implements Observer{
    private Line l;
    public Console (Line line) {
        this.l = line;
        l.addObserver(this);
    }

    public void update(Observable obs, Object arg) {
        System.out.flush();
        System.out.print("\u001b[" + (l.getPos() + 1) + "G");
    }
}

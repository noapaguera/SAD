import java.util.Observable;
import java.util.Observer;

@SuppressWarnings("deprecation")
public class Console implements Observer{
    public Console (Observable o) {
        Observable Obs = o;
    }

    public void update(Observable Obs, Object arg) {
        System.out.println(arg);
    }
}

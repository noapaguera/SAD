package EditableMVCPropertyChange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class Console implements PropertyChangeListener {
    @Override
    public void propertyChange(PropertyChangeEvent event) {
        switch (event.getPropertyName()) {
            case "str":
                int index = ((Line) event.getSource()).getPos();
                System.out.print("\u001b[0K" + ((String) event.getNewValue()).substring(index - 1) + "\u001b[" + (index + 1) + "G");
                break;
            case "strBackspace":
                System.out.print("\u001b[D" + "\u001b[P");
                break;
            case "strDelete":
                System.out.print("\u001b[P");
                break;
            case "right":
                System.out.print("\u001b[C");
                break;
            case "left":
                System.out.print("\u001b[D");
                break;
            case "home":
                System.out.print("\u001b[G");
                break;
            case "fin":
                System.out.print("\u001b[" + ((Integer) event.getNewValue() + 1) + "G");
                break;        
        }
    }
}

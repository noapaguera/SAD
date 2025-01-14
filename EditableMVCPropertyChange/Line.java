package EditableMVCPropertyChange;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Line {

	private int cursorPos;
	private String string;
	private boolean insert;
	PropertyChangeSupport pcs = new PropertyChangeSupport(this);

	public Line(PropertyChangeListener pcl) {
		cursorPos = 0;
		string = "";
		insert = false;
		pcs.addPropertyChangeListener(pcl);
	}

	public int getPos() {
		return this.cursorPos;
	}

	public void addChar(char ch) { //revisar insert
		String strAux = string;
		if (this.insert) {
			string = string.substring(0, cursorPos) + ch + string.substring(cursorPos);
		} else {
			if (cursorPos <= string.length()) {
				string += ch;
			} else {
				string = string.substring(0, cursorPos) + ch + string.substring(cursorPos + 1);
			}
		}
		cursorPos++;
		pcs.firePropertyChange(new PropertyChangeEvent(this, "str", strAux, string));
	}

	/* public void addChar(char ch) {
		if (this.cursorPos >= this.string.length()) {
			this.line.add(this.cursorPos, ch);
		} else {
			if (this.insert) {
				this.line.set(this.cursorPos, ch);
			} else {
				System.out.print("\u001b[1@");
				this.line.add(this.cursorPos, ch);
			}
		}
		this.cursorPos++;
	} */

	public void backspace() {
		String strAux = string;
		if (this.cursorPos > 0) {
			this.cursorPos--;
			string = string.substring(0, cursorPos - 1) + string.substring(cursorPos);
			pcs.firePropertyChange(new PropertyChangeEvent(this, "strBackspace", strAux, string));
		}
	}

	public void delete() {
		String strAux = string;
		if (this.cursorPos >= 0 && this.cursorPos < this.string.length()) {
			string = string.substring(0, cursorPos) + string.substring(cursorPos + 1);
			pcs.firePropertyChange(new PropertyChangeEvent(this, "strDelete", strAux, string));
		}
	}

	public void insert() {
		this.insert = !this.insert;
	}

	public void moveRight() {
		if (this.cursorPos < string.length()) {
			pcs.firePropertyChange(new PropertyChangeEvent(this, "right", cursorPos, cursorPos +=1));
		}
	}

	public void moveLeft() {
		if (cursorPos > 0) {
			pcs.firePropertyChange(new PropertyChangeEvent(this, "left", cursorPos, cursorPos -=1));
		}
	}

	public void home() {
		pcs.firePropertyChange(new PropertyChangeEvent(this, "home", cursorPos, cursorPos = 0));
	}

	public void fin() {
		pcs.firePropertyChange(new PropertyChangeEvent(this, "fin", cursorPos, cursorPos = string.length()));
	}

	public String getLine() {
		return this.string;
	}

	public String toString() {
		return string;
	}
}

package EditableBufferedReaderMVC;

import java.util.ArrayList;
import java.util.Observable;

public class Line extends Observable{
    private int cursorPos;
	private ArrayList<Character> line;
	private boolean insert;

	public Line() {
		cursorPos = 0;
		insert = false;
		line = new ArrayList<Character>();
		Console c = new Console(this);
		this.addObserver(c);
	}

	public int getPos() {
		return this.cursorPos;
	}

	public void addChar(char ch) {
		if (this.cursorPos >= this.line.size()) {
			this.line.add(this.cursorPos, ch);
		} else {
			if (this.insert) {
				this.line.set(this.cursorPos, ch);
			} else {
				this.line.add(this.cursorPos, ch);
			}
		}
		this.cursorPos++;
		this.setChanged();
		this.notifyObservers(ch);
	}

	public void backspace() {
		if (this.cursorPos > 0) {
			this.cursorPos--;
			this.line.remove(this.cursorPos);
			System.out.print("\u001b[D"); // CSI n D 	Cursor Back. Moves the cursor n (default 1) cells in the given direction. 
			System.out.print("\u001b[P");
		}
	}

	public void delete() {
		if (this.cursorPos >= 0 && this.cursorPos < this.line.size()) {
			this.line.remove(this.cursorPos);
/* 			this.setChanged();
			this.notifyObservers("\u001b[C"); // CSI n C 	Cursor Forward */
			System.out.print("\u001b[P");
		}
	}

	public void insert() {
		this.insert = !this.insert;
	}

	public void moveRight() {
		if (this.cursorPos < line.size()) {
			this.cursorPos++;
			this.setChanged();
			this.notifyObservers("\u001b[C"); // CSI n C 	Cursor Forward
		}
	}

	public void moveLeft() {
		if (cursorPos > 0) {
			this.cursorPos--;
			this.setChanged();
			this.notifyObservers("\u001b[D");
		}
	}

	public void home() {
		this.cursorPos = 0;
		this.setChanged();
		this.notifyObservers("\u001b[G"); // CSI n G 	Moves the cursor to column n (default 1)
	}

	public void fin() {
		this.cursorPos = this.line.size();
		this.setChanged();
		this.notifyObservers("\u001b[" + (this.line.size() + 1) + "G");
	}

	public String getLine() {
		return this.line.toString();
	}

	public String toString() {
		String str = "";
		for (int i = 0; i < this.line.size(); i++) {
			str = str + this.line.get(i).toString();
		}
		return str;
	}
}

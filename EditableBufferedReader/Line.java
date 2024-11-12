package EditableBufferedReader;

import java.util.*;

public class Line {

	private int cursorPos;
	private ArrayList<Character> line;
	private boolean insert;

	public Line() {
		cursorPos = 0;
		line = new ArrayList<Character>();
		insert = false;
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
				System.out.print("\u001b[1@");
				this.line.add(this.cursorPos, ch);
			}
		}
		this.cursorPos++;
	}

	public void backspace() {
		if (this.cursorPos > 0) {
			this.cursorPos--;
			this.line.remove(this.cursorPos);
			System.out.print("\u001b[D"); // Mou el cursor 1 a l'esquerra
			System.out.print("\u001b[P"); // Esborra el caracter a la posició del cursor
		}
	}

	public void delete() {
		if (this.cursorPos >= 0 && this.cursorPos < this.line.size()) {
			this.line.remove(this.cursorPos);
			System.out.print("\u001b[P");
		}
	}

	public void insert() {
		this.insert = !this.insert;
	}

	public void moveRight() {
		if (this.cursorPos < line.size()) {
			this.cursorPos++;
			System.out.print("\u001b[C");
		}
	}

	public void moveLeft() {
		if (cursorPos > 0) {
			this.cursorPos--;
			System.out.print("\u001b[D");
		}
	}

	public void home() {
		this.cursorPos = 0;
		System.out.print("\u001b[G");
	}

	public void fin() {
		this.cursorPos = this.line.size();
		System.out.print("\u001b[" + (this.line.size() + 1) + "G");
	}

	public String getLine() {
		return this.line.toString();
	}

	public String toString() {
		String str = this.line.get(0).toString();
		for (int i = 1; i < this.line.size(); i++) {
			str = str + this.line.get(i).toString();
		}
		return str;
	}
}

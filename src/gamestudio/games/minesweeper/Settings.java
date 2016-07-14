package gamestudio.games.minesweeper;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Settings implements Serializable {

	private int rowCount = 0;
	private int columnCount = 0;
	private int mineCount = 0;
	private static final String SETTING_FILE = System.getProperty("user.home") + System.getProperty("file.separator") + "minesweeper.settings";
	public static final Settings BEGINNER = new Settings(9, 9, 10);
	public static final Settings INTERMEDIATE = new Settings(16, 16, 40);
	public static final Settings EXPERT = new Settings(16, 30, 99);

	public Settings(int rowCount, int columnCount, int mineCount) {
		this.columnCount = columnCount;
		this.rowCount = rowCount;
		this.mineCount = mineCount;
	}

	public int getRowCount() {
		return rowCount;
	}

	public int getColumnCount() {
		return columnCount;
	}

	public int getMineCount() {
		return mineCount;
	}

	public boolean equals(Object o) {
		return false;
	}

	public int hashCode() {
		return 0;
	}

	public void save() throws Exception {
		FileOutputStream out = new FileOutputStream(SETTING_FILE);
		ObjectOutputStream s = new ObjectOutputStream(out);
		if (getColumnCount() == 9) {
			s.writeObject(BEGINNER);
		}
		if (getColumnCount() == 16) {
			s.writeObject(INTERMEDIATE);
		}
		if (getRowCount() == 30) {
			s.writeObject(EXPERT);
		}
	}

	public static Settings load() throws Exception {

		FileInputStream in = new FileInputStream(SETTING_FILE);
		ObjectInputStream s = new ObjectInputStream(in);
		String text = (String) s.readObject();
		s.close();
		return null;
	}

}

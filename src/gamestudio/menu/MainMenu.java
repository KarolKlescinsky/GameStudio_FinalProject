package gamestudio.menu;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

public class MainMenu {

	public static void main(String[] args) throws SQLException {

		ConsoleUI ui = new ConsoleUI();
		try {
			ui.run();
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

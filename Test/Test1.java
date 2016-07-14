import static org.junit.Assert.*;

import org.junit.Test;

import minesweeper.core.Field;
import minesweeper.core.Tile;

public class Test1 {

	@Test
	public void testNumberOfMinesInField() {

		int rowCount = 9;
		int columnCount = 9;
		int mineCount = 10;

		Field field = new Field(rowCount, columnCount, mineCount);
		assertTrue(" Number of mines is same.", field.getMineCount() == mineCount);

	}
	
	public void testGetRowCount (){
		
		int rowCount = 9;
		int columnCount = 9;
		int mineCount = 10;

		Field field = new Field(rowCount, columnCount, mineCount);
		assertTrue(" Number of rows is same.", field.getRowCount() == rowCount);

	}
	
	public void testColumnCount(){
		
		int rowCount = 9;
		int columnCount = 9;
		int mineCount = 10;

		Field field = new Field(rowCount, columnCount, mineCount);
		assertTrue(" Number of columns is same.", field.getColumnCount()== columnCount);
		
	}
	
	public void testMarkTileMethod(){
		
		int rowCount = 9;
		int columnCount = 9;
		int mineCount = 10;
		
		Field field = new Field(rowCount, columnCount, mineCount);

		for (int row = 0; row < field.getRowCount(); row++) {
			for (int column = 0; column < field.getColumnCount(); column++) {

			assertTrue("Test if all tiles are closed in the beginning." , field.getTile(row, column).getState() == Tile.State.CLOSED) ;
					
			}
		}
	}

}

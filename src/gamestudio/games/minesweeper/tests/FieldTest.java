//package gamestudio.games.minesweeper.tests;
//
//import static org.junit.Assert.*;
//import org.junit.Test;
//
//import gamestudio.games.minesweeper.core.Clue;
//import gamestudio.games.minesweeper.core.Field;
//import gamestudio.games.minesweeper.core.GameState;
//import gamestudio.games.minesweeper.core.Tile;
//
//public class FieldTest {
//
//	static final int ROWS = 9;
//	static final int COLUMNS = 9;
//	static final int MINES = 10;
//
//	@Test
//	public void isSolved() {
//		Field field = new Field(ROWS, COLUMNS, MINES);
//
//		assertEquals(GameState.PLAYING, field.getState());
//
//		int open = 0;
//		for (int row = 0; row < field.getRowCount(); row++) {
//			for (int column = 0; column < field.getColumnCount(); column++) {
//				if (field.getTile(row, column) instanceof Clue) {
//					field.openTile(row, column);
//					open++;
//				}
//				if (field.getRowCount() * field.getColumnCount() - open == field.getMineCount()) {
//					assertEquals(GameState.SOLVED, field.getState());
//				} else {
//					assertNotSame(GameState.FAILED, field.getState());
//				}
//			}
//		}
//
//		assertEquals(GameState.SOLVED, field.getState());
//	}
//
//	@Test
//	public void generate() {
//
//		int clueCount = 0;
//		Field field = new Field(ROWS, COLUMNS, MINES);
//
//		assertTrue(" Number of mines is same.", field.getMineCount() == MINES);
//		assertTrue(" Number of rows is same.", field.getRowCount() == ROWS);
//		assertTrue(" Number of columns is same.", field.getColumnCount() == COLUMNS);
//
//		for (int row = 0; row < ROWS; row++) {
//			for (int column = 0; column < COLUMNS; column++) {
//
//				assertNotNull(field.getTile(row, column));
//				assertEquals(MINES, field.getMineCount());
//
//			}
//		}		
//		
////		for (int row = 0; row < ROWS; row++) {
////			for (int column = 0; column < COLUMNS; column++) {
////		
////				Tile tempTile = field.getTile(row, column);
////				Clue tempClue = (Clue) tempTile;
////				tempClue.getValue();
////
////				if (tempClue.getValue() > 0) {
////					clueCount++;
////				}
////
////
////			}
////		}
//	//	assertEquals((ROWS * COLUMNS) - MINES, clueCount);
//
//
//	}
//
//}

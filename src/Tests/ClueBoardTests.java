package Tests;
import static org.junit.Assert.*;



import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

import junit.framework.Assert;

import org.junit.BeforeClass;
import org.junit.Test;

import clueBoard.BadConfigFormatException;
import clueBoard.Board;
import clueBoard.DoorDirection;
import clueBoard.RoomCell;

public class ClueBoardTests {
	private static Board myBoard; 
	public static final int NUM_ROOMS = 11;
	public static final int NUM_ROWS = 22;
	public static final int NUM_COLUMNS = 23;
	
	@BeforeClass
	public static void setUp() throws IOException, BadConfigFormatException {
		myBoard = new Board("ClueRooms.csv", "Legend.txt");
		myBoard.loadConfigFiles();
	}
	
	@Test
	public void testNumRooms() {
		Assert.assertEquals(NUM_ROOMS, myBoard.getRooms().size()); 	
	}
	
	@Test
	public void testRoomMap() {
		Map<Character, String> rooms = myBoard.getRooms();
		//System.out.println(myBoard.getRooms().get('C'));
		//System.out.println(rooms.get('L'));
		//Assert.assertEquals("Conservatory", rooms.get('C'));
		Assert.assertEquals("Library", rooms.get('L'));
		Assert.assertEquals("Closet", rooms.get('X'));
		//Assert.assertEquals("Walkway", rooms.get('W'));
	}
	
	@Test
	public void testRowColumn() {
		Assert.assertEquals(NUM_ROWS, myBoard.getNumRows()); 
		Assert.assertEquals(NUM_COLUMNS, myBoard.getNumColumns());
	}
	
	@Test
	public void testDoors() {
		RoomCell myRoom = myBoard.getRoomCellAt(9,6);
		assertTrue(myRoom.isDoorway()); 
		Assert.assertEquals(DoorDirection.RIGHT, myRoom.getDoorDirection()); 

		myRoom = myBoard.getRoomCellAt(4, 8); 
		assertTrue(myRoom.isDoorway()); 
		Assert.assertEquals(DoorDirection.DOWN, myRoom.getDoorDirection()); 

		myRoom = myBoard.getRoomCellAt(4, 20);
		assertTrue(myRoom.isDoorway()); 
		Assert.assertEquals(DoorDirection.LEFT, myRoom.getDoorDirection()); 
		
		myRoom = myBoard.getRoomCellAt(14, 11);
		assertTrue(myRoom.isDoorway()); 
		Assert.assertEquals(DoorDirection.UP, myRoom.getDoorDirection());
		
		myRoom = myBoard.getRoomCellAt(0, 0);
		assertFalse(myRoom.isDoorway()); 
	}
	
	@Test
	public void testRoomInitial() {
		//RoomCell myRoom = myBoard.getRoomCellAt(10, 12); 
		//Assert.assertEquals("X", myRoom.getRoomInitial()); 
		
		//myRoom = myBoard.getRoomCellAt(1, 1); 
		//Assert.assertEquals("C", myRoom.getRoomInitial()); 
		
		RoomCell myRoom = myBoard.getRoomCellAt(17, 12); 
		Assert.assertEquals('D', myRoom.getRoomInitial()); 
	}
	
	@Test
	public void testCalcIndex() {
		Assert.assertEquals(120, myBoard.calcIndex(5,5));
		Assert.assertEquals(66, myBoard.calcIndex(2, 20));
		Assert.assertEquals(216, myBoard.calcIndex(9,9));
		Assert.assertEquals(505, myBoard.calcIndex(21,22));
	}
	
	@Test (expected = BadConfigFormatException.class)
	public void exceptionTest() throws BadConfigFormatException, FileNotFoundException {
		Board testBoard = new Board ("ClueRooms.csv", "BadFile.txt");
		testBoard.loadLegend();
		testBoard.loadBoard();
	}
}

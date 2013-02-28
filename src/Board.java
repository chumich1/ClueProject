import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Board {
	private ArrayList<BoardCell> cells;
	Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	String legend;
	String board;

	public void loadConfigFiles(){

		try {
			loadLegend();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			loadBoard();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Board() {

	}

	public Board(String csv, String txt) {
		rooms = new HashMap<Character, String>();
		cells = new ArrayList<BoardCell>();
		legend = txt;
		board = csv;
	}

	public void loadLegend() throws BadConfigFormatException, FileNotFoundException{

		FileReader reader = new FileReader(legend);
		Scanner in = new Scanner(reader);
		String array[] = new String[2];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = new String();
		}

		while(in.hasNextLine()){
			String line = in.nextLine();
			array = line.split(",");

			// Throw a BadConfigFormatException when the size of the split array is not 2. 
			// This works for the test, but is not a perfect check since files could still be 
			// configured incorrectly even if they only have the comma. 
			if (array.length != 2) {
				throw new BadConfigFormatException();
			}

			rooms.put(array[0].charAt(0), array[1]);
		}

		in.close();
	}

	public void loadBoard() throws  BadConfigFormatException, FileNotFoundException{
		FileReader reader = new FileReader(board);
		Scanner in = new Scanner(reader);
		String array[] = new String[23];
		numRows = 0;
		
		for(int i = 0; i < array.length; i++) {
			array[i] = new String();
		}
		
		while(in.hasNextLine()){
			String line = in.nextLine();

			array = line.split(",");

			for(int i = 0; i < array.length; i++) {
				numColumns = array.length;
				
				if(array[i].charAt(0) == 'W'){
					cells.add(new WalkwayCell());
				} else {
					if (array[i].length() > 1) {
						switch (array[i].charAt(1)) {
						case 'U':
							cells.add(new RoomCell(array[i].charAt(0), DoorDirection.UP));
							break; 
						case 'D':
							cells.add(new RoomCell(array[i].charAt(0), DoorDirection.DOWN));
							break; 
						case 'L':
							cells.add(new RoomCell(array[i].charAt(0), DoorDirection.LEFT));
							break; 
						case 'R':
							cells.add(new RoomCell(array[i].charAt(0), DoorDirection.RIGHT));
							break; 
						}						
					} else {
						cells.add(new RoomCell(array[i].charAt(0), DoorDirection.NONE));
					}
				}
			}

			rooms.put(array[0].charAt(0), array[1]);
			numRows++;
		}

		in.close();
	}

	public int calcIndex(int r, int c){
		return (r*numColumns + c);
	}

	public RoomCell getRoomCellAt(int r, int c){
		int location = calcIndex(r,c);
		
		if (cells.get(location).isWalkway()) 
			return null;
		else
			return new RoomCell(cells.get(location).getRoomInitial(), cells.get(location).getDoorDirection());
	}

	public ArrayList<BoardCell> getCells() {
		return cells;
	}

	public Map<Character, String> getRooms() {
		return rooms;
	}

	public int getNumRows() {
		return numRows;
	}

	public int getNumColumns() {
		return numColumns;
	}

}

/*
 * Authors:
 * Julia Morin
 * Andrew Chumich
 */

package clueBoard;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Board {
	private ArrayList<BoardCell> cells;
	Map<Character, String> rooms;
	private int numRows;
	private int numColumns;
	String legend;
	String board;
	private Map<Integer, LinkedList<Integer>> adjMtx;
	private boolean[] visited;
	private Set targets; 
	private int originalCell; 

	public void loadConfigFiles(){

		try{
			loadLegend();
			loadBoard();
		}
		catch (BadConfigFormatException e){
			e.getMessage();
		}
		catch (FileNotFoundException e){
			e.getMessage();
		}
		
	}

	public Board() {

	}

	public Board(String csv, String txt) {
		rooms = new HashMap<Character, String>();
		cells = new ArrayList<BoardCell>();
		legend = txt;
		board = csv;
		adjMtx = new HashMap<Integer, LinkedList<Integer>>();
		targets = new HashSet();
		visited = new boolean[16]; 
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
			//System.out.println(array[0]+ array[1]);

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
						default:
							throw new BadConfigFormatException();
							
						}						
					} else {
						cells.add(new RoomCell(array[i].charAt(0), DoorDirection.NONE));
					}
				}
			}
			//proably left over from copypasta
			//rooms.put(array[0].charAt(0), array[1]);
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
	
	public BoardCell getCellAt(int i){
		return cells.get(i);
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
	
	
	public void calcAdjacencies(){
		LinkedList<Integer> tempList = new LinkedList<Integer>();
		for(int r = 0; r < 4; r++){
			
			for(int c = 0; c < 4; c++){
				tempList = new LinkedList<Integer>();
				if(r+1 <4)
					tempList.add(calcIndex(r+1, c));
				
				if(r-1 >=0)
					tempList.add(calcIndex(r-1, c));
				
				if( c+1 <4)
					tempList.add(calcIndex(r, c+1));
				
				if(c-1 >=0)
					tempList.add(calcIndex(r, c-1));
				
			
				adjMtx.put(calcIndex(r,c), tempList);
			}
		}
		
	}
	
	
	// TrackStart is a helper method to keep track of the original cell where you start.
	// It then calls startTargets which is called recursively to calculate the targets.
	public void trackStart (int cell, int steps) {
		originalCell = cell; 
		startTargets(cell, steps);
	}
	
	// startTargets is a recursive method that calculates all possible targets.
	public void startTargets(int cell, int steps){
		visited[cell] = true; 
		
		for (int i = 0; i < adjMtx.get(cell).size(); i++) { 
			int currentCell = adjMtx.get(cell).get(i); 
			
			if (steps > 0 && (visited[currentCell] == false)){ 
				startTargets(currentCell, steps - 1);
			}
				
			if (steps == 1 && currentCell != originalCell)
				targets.add(currentCell);
		}
		
		visited[cell] = false; 
	}
	
	public Set getTargets(){
		return targets;
	}
	
	public LinkedList<Integer> getAdjList(int cell){
		return adjMtx.get(cell);
	}

	// Converts rows and columns into a single index value.


}

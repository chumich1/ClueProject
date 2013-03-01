package clueBoard;

public class RoomCell extends BoardCell{
	
	private DoorDirection doorDirection;
	private char roomInitial;
	
	public RoomCell(char a, DoorDirection direction){
		roomInitial = a;
		doorDirection = direction;
	}

	@Override
	public boolean isRoom(){
		return true;
	}
	
	@Override
	public boolean isDoorway(){
		if (getDoorDirection() == DoorDirection.NONE)
			return false;
		else 
			return true; 
	}
	
	@Override
	public void draw() {
		// TODO Auto-generated method stub
	}

	@Override
	public DoorDirection getDoorDirection() {
		return doorDirection;
	}
	
	@Override
	public char getRoomInitial() {
		return roomInitial;
	}
}

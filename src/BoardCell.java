
public abstract class BoardCell{
	
	private Integer row;
	private Integer column;
	
	public boolean isWalkway(){
		return false;
	}
	
	public boolean isRoom(){
		return false;
	}
	
	public boolean isDoorway(){
		return false;
	}
	
	public char getRoomInitial() {
		return 'W'; 
	}
	
	public DoorDirection getDoorDirection() {
		return DoorDirection.NONE;
	}
	
	abstract public void draw();

}

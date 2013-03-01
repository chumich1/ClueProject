package clueBoard;

public enum DoorDirection {
	UP ("U"), DOWN ("D"), LEFT ("L"), RIGHT ("R"), NONE ("N");
	
	private String value; 
	
	DoorDirection(String myValue) {
		value = myValue;
	}
	
	public String toString() {
		return value; 
	}
	
}

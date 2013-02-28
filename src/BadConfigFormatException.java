
public class BadConfigFormatException extends Exception{

	public BadConfigFormatException(){
		
	}
	
	public BadConfigFormatException(String a){
		super(a);
	}
	
	@Override
	public String toString() {
		return "BadConfigFormatException [toString()=" + super.toString() + "]";
	}
	
	
	
}

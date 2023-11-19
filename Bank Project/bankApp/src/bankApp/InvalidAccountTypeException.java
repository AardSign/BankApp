package bankApp;

public class InvalidAccountTypeException extends Exception {

	public InvalidAccountTypeException() {
		super("Invalid account Type selected");
	}
}

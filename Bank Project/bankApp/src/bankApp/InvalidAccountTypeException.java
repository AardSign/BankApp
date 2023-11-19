package bankApp;

public class InvalidAccountTypeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidAccountTypeException() {
		super("Invalid account Type selected");
	}
}

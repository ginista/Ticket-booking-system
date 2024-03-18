package exception;

public class InvalidBookingIdException extends RuntimeException {

	public InvalidBookingIdException(String message) {
		super(message);
	}
}

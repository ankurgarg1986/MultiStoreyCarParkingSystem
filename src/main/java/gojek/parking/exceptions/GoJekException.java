package gojek.parking.exceptions;

/**
 * Checked Custom Exception to be thrown by the API
 * @author agarg
 *
 */
public class GoJekException extends Exception {

	
	private static final long serialVersionUID = 8192083372488142312L;

	public GoJekException(){

	}

	public GoJekException(String message){
		super(message);
	}

	public GoJekException(Throwable cause){
		super(cause);
	}

	public GoJekException(String message, Throwable cause){
		super(message, cause);
	}

}

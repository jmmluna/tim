package es.jmmluna.tim.domain.model;

public class NotFoundItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundItemException() {
		
	}
	
	public NotFoundItemException(String errorMessage) {
        super(errorMessage);
    }
}

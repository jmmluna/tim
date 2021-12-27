package es.jmmluna.tim.domain.model.budget;

public class IdentifierNotAllowedException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IdentifierNotAllowedException() {
		
	}
	
	public IdentifierNotAllowedException(String errorMessage) {
        super(errorMessage);
    }
}

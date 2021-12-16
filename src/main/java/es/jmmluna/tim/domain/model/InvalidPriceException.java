package es.jmmluna.tim.domain.model;

public class InvalidPriceException extends RuntimeException  {

	private static final long serialVersionUID = 1L;

	public InvalidPriceException(String errorMessage) {
        super(errorMessage);
    }
}

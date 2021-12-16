package es.jmmluna.tim.domain.model;

public class InvalidHourException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InvalidHourException(String errorMessage) {
        super(errorMessage);
    }
}

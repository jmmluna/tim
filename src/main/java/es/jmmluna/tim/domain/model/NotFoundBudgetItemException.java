package es.jmmluna.tim.domain.model;

public class NotFoundBudgetItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public NotFoundBudgetItemException() {
		
	}
	
	public NotFoundBudgetItemException(String errorMessage) {
        super(errorMessage);
    }
}

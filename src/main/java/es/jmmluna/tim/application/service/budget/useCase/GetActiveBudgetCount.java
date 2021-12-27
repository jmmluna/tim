package es.jmmluna.tim.application.service.budget.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class GetActiveBudgetCount {
	
	private BudgetRepository repository;

	@Autowired
	public GetActiveBudgetCount(BudgetRepository repository) {
		this.repository = repository;
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}

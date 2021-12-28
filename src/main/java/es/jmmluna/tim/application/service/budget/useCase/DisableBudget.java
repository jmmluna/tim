package es.jmmluna.tim.application.service.budget.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class DisableBudget {

	@Autowired
	private BudgetRepository repository;
	
	@Autowired
	public DisableBudget(BudgetRepository repository) {
		this.repository = repository;
	}

	public void execute(UUID uuid) {
		var budget = this.repository.findById(BudgetId.of(uuid));				
		this.repository.delete(budget);
	}

}

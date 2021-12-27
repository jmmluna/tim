package es.jmmluna.tim.application.service.budget.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetMapper;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class GetBudget {

	@Autowired
	private BudgetMapper mapper;
	
	private BudgetRepository repository;

	@Autowired
	public GetBudget(BudgetRepository repository) {
		this.repository = repository;
	}

	public BudgetDTO execute(UUID uuid) {
		var budget = this.repository.findById(BudgetId.of(uuid));
		return mapper.toDTO(budget);

	}

}

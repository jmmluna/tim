package es.jmmluna.tim.application.service.budget.useCase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetMapper;
import es.jmmluna.tim.domain.model.IdentifierNotAllowedException;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class CreateBudget {

	@Autowired
	private BudgetRepository budgetRepository;
	
	@Autowired
	private BudgetMapper mapper;

	public BudgetDTO execute(BudgetDTO budgetDTO) {
		if (budgetDTO.getUuid() != null)
			throw new IdentifierNotAllowedException("Not null UUID are not allowed");

		budgetDTO.setUuid(budgetRepository.getNextIdentifier());
		var budget = budgetRepository.save(mapper.toModel(budgetDTO));
		return mapper.toDTO(budget);
	}
}

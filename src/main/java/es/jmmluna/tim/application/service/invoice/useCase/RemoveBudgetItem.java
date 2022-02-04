package es.jmmluna.tim.application.service.invoice.useCase;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.budget.BudgetMapper;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RemoveBudgetItem {

	@Autowired
	private BudgetRepository budgetRepository;
	@Autowired
	private BudgetMapper mapper;

	public BudgetDTO execute(UUID bugetId, BudgetItemDTO budgetItemDTO) {	
		var budget = budgetRepository.findById(BudgetId.of(bugetId));		
		budget.remove(mapper.toBudgetItem(budgetItemDTO));
		
		var savedBudget = budgetRepository.save(budget);				
		return mapper.toDTO(savedBudget);			
	}
}

package es.jmmluna.tim.application.service.budget.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.budget.BudgetMapper;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class AddBudgetItem {

	@Autowired
	private BudgetRepository budgetRepository;
	@Autowired
	private BudgetMapper mapper;

	public BudgetDTO execute(UUID bugetId, BudgetItemDTO budgetItemDTO) {
		Budget savedBudget =null;
		try {
		var budget = budgetRepository.findById(BudgetId.of(bugetId));		
		budget.add(mapper.toBudgetItem(budgetItemDTO));
		
		savedBudget = budgetRepository.save(budget);
		
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return mapper.toDTO(savedBudget);
		
		
	}
}

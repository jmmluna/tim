package es.jmmluna.tim.application.service.budget.useCase;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetMapper;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Service
public class GetBudgetList {

	private BudgetRepository repository;
	@Autowired
	private BudgetMapper mapper;

	@Autowired
	public GetBudgetList(BudgetRepository repository) {
		this.repository = repository;
	}

	public List<BudgetDTO> execute(EElementList type) {
		List<BudgetDTO> customers = new ArrayList<BudgetDTO>();

		if (type == EElementList.ALL)
			customers = this.toBudgetDTOList(this.repository.findAll());
		else if (type == EElementList.ACTIVE)
			customers = this.toBudgetDTOList(this.repository.getActives());
		else
			customers = this.toBudgetDTOList(this.repository.getInactives());

		return customers;
	}

	private List<BudgetDTO> toBudgetDTOList(Iterable<Budget> budgets) {
		var dtos = new ArrayList<BudgetDTO>();

		budgets.forEach(budget -> dtos.add(mapper.toDTO(budget)));
		return dtos;
	}

}

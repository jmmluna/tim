package es.jmmluna.tim.application.service.work.useCase;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkMapper;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.work.WorkId;
import es.jmmluna.tim.domain.model.work.WorkRepository;

@Service
public class GetWork {

	@Autowired
	private WorkRepository repository;
	@Autowired
	private WorkMapper mapper;

	public WorkDTO execute(UUID uuid) {
		var work = this.repository.findById(WorkId.of(uuid));
		return mapper.toDTO(work);
	}
	
	public WorkDTO execute(BudgetDTO budgetDTO) {
		var work = this.repository.findByBudget(BudgetId.of(budgetDTO.getUuid()));
		return mapper.toDTO(work);
	}
}

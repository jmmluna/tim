package es.jmmluna.tim.domain.model.budget;

import java.util.List;

import es.jmmluna.tim.domain.model.CommonRepository;

public interface BudgetRepository extends CommonRepository<Budget, BudgetId>{

	public long getActiveCount();

	public List<Budget> getActives();

	public List<Budget> getInactives();	

}

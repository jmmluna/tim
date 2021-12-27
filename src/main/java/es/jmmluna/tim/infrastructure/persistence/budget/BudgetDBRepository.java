package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetRepository;

@Repository
public class BudgetDBRepository implements BudgetRepository {
	
	@Autowired
	private BudgetEntityMapper mapper;

	@Autowired
	private BudgetJpaRepository budgetRepository;

	@Override
	public long getActiveCount() {
		return budgetRepository.countByExpirationDate(null);
	}

	@Override
	public Budget save(Budget budget) {				
		var savedEntity = budgetRepository.save(mapper.toEntity(budget));
		return mapper.toModel(savedEntity);
	}

	@Override
	public Budget findById(BudgetId budgetId) {
		Optional<BudgetEntity> result = budgetRepository.findById(budgetId.getValue());
		var entity = result.get();
		return mapper.toModel(entity);
	}

	@Override
	public void delete(Budget budget) {
		budget.setExpirationDate(new Date());
		this.save(budget);
	}

	@Override
	public List<Budget> findAll() {
		var entities = budgetRepository.findAll();
		return toList(entities);
	}

	@Override
	public List<Budget> getActives() {
		var entities = budgetRepository.findByExpirationDate(null);
		return toList(entities);

	}

	@Override
	public List<Budget> getInactives() {
		var entities = budgetRepository.findByExpirationDateIsNotNull();
		return toList(entities);
	}

	private List<Budget> toList(List<BudgetEntity> budgetEntities) {
		var budgets = new ArrayList<Budget>();
		budgetEntities.forEach(entity -> budgets.add(mapper.toModel(entity)));
		return budgets;
	}

	@Override
	public UUID getNextIdentifier() {		
		return UUID.randomUUID();
	}
}

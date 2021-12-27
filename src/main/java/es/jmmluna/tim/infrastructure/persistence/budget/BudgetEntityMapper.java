package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;

@Component
public class BudgetEntityMapper {

	public Budget toModel(BudgetEntity entity) {
		
		return new Budget(BudgetId.of(entity.getUuid()), 
				entity.getBudgetNumber(), 
				entity.getDescription(),
				entity.getYear(),
				entity.getDate(), 
				toBudgetItemList(entity.getBudgetItems()), 
				entity.getExpirationDate());

	}

	public BudgetEntity toEntity(Budget model) {
		BudgetEntity entity = new BudgetEntity();
		entity.setUuid(model.getBudgetId().getValue());
		entity.setBudgetNumber(model.getBudgetNumber());		
		entity.setDescription(model.getDescription());
		entity.setYear(model.getYear());
		entity.setDate(model.getDate());
		entity.setExpirationDate(model.getExpirationDate());
		
		//TODO: falta por implementar la conversión de items del modelo a items de entidad
		entity.setBudgetItems(new ArrayList<BudgetItemEntity>());
		return entity;
	}
	
	private List<BudgetItem> toBudgetItemList(List<BudgetItemEntity> budgetItemEntities) {
		var budgetItems = new ArrayList<BudgetItem>();
		
		budgetItemEntities.forEach(entity -> budgetItems.add(toBudgetItem(entity)));
		return budgetItems;
	}
	
	private BudgetItem toBudgetItem(BudgetItemEntity entity) {
		return new BudgetItem(BudgetItemId.of(entity.getUuid()), entity.getDescription(), entity.getAmount(), Price.of(entity.getPrice()));
		
	}
}

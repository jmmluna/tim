package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
		
		entity.setBudgetItems(toBudgetItemEntityList(entity, model.getBudgetItems()));
		return entity;
	}
	
	private List<BudgetItem> toBudgetItemList(List<BudgetItemEntity> budgetItemEntities) {
		var budgetItems = new ArrayList<BudgetItem>();
		
		budgetItemEntities.forEach(entity -> budgetItems.add(toBudgetItem(entity)));
		return budgetItems;
	}
	
	private List<BudgetItemEntity> toBudgetItemEntityList(BudgetEntity budgetEntity, List<BudgetItem> budgetItems) {
		var budgetItemEntities = new ArrayList<BudgetItemEntity>();
		
		budgetItems.forEach(budgetItem -> budgetItemEntities.add(toBudgetItemEntity(budgetEntity, budgetItem)));
		return budgetItemEntities;
	}
	
	private BudgetItem toBudgetItem(BudgetItemEntity entity) {		
//		log.info("*********************************************Precio de la Entidad---->" + entity.getUuid());
//		log.info("*********************************************Precio de la Entidad---->" + entity.getPrice());
		return new BudgetItem(BudgetItemId.of(entity.getUuid()), entity.getDescription(), entity.getAmount(), Price.of(entity.getPrice()));
	}
	
	private BudgetItemEntity toBudgetItemEntity(BudgetEntity budgetEntity, BudgetItem budgetItem) {
		var budgetItemEntity = new BudgetItemEntity();
		budgetItemEntity.setUuid(budgetItem.getBudgetItemId().getValue());
		budgetItemEntity.setBudgetEntity(budgetEntity);
		budgetItemEntity.setDescription(budgetItem.getDescription());
		budgetItemEntity.setPrice(budgetItem.getPrice().getValue());
		budgetItemEntity.setAmount(budgetItem.getQuantity());	
		return budgetItemEntity;
		
	}
}

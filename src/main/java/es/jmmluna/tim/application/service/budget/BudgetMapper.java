package es.jmmluna.tim.application.service.budget;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.budget.Budget;
import es.jmmluna.tim.domain.model.budget.BudgetId;
import es.jmmluna.tim.domain.model.budget.BudgetItem;
import es.jmmluna.tim.domain.model.budget.BudgetItemId;

@Component
public class BudgetMapper {

	public BudgetDTO toDTO(Budget budget) {

		return new BudgetDTO(budget.getBudgetId().getValue(), budget.getDescription(), budget.getBudgetNumber(),
				budget.getYear(), budget.getDate(), toBudgetItemDTOList(budget.getBudgetItems()),
				budget.getExpirationDate());

	}

	public Budget toModel(BudgetDTO budgetDTO) {

		return new Budget(BudgetId.of(budgetDTO.getUuid()), budgetDTO.getBudgetNumber(), budgetDTO.getDescription(),
				budgetDTO.getYear(), budgetDTO.getDate(), toBudgetItemList(budgetDTO.getBudgetItems()),
				budgetDTO.getExpirationDate());
	}

	public BudgetItem toBudgetItem(BudgetItemDTO budgetItemDTO) {
		return new BudgetItem(BudgetItemId.of(budgetItemDTO.getUuid()), budgetItemDTO.getDescription(),
				budgetItemDTO.getAmount(), Price.of(budgetItemDTO.getPrice()));
	}

	private BudgetItemDTO toBudgetItemDTO(BudgetItem budgetItem) {
		return new BudgetItemDTO(budgetItem.getBudgetItemId().getValue(), budgetItem.getDescription(),
				budgetItem.getQuantity(), budgetItem.getPrice().getValue());
	}

	private List<BudgetItem> toBudgetItemList(List<BudgetItemDTO> budgetItemsDTO) {
		var budgetItems = new ArrayList<BudgetItem>();

		budgetItemsDTO.forEach(budgetItemDTO -> budgetItems.add(toBudgetItem(budgetItemDTO)));
		return budgetItems;
	}

	private List<BudgetItemDTO> toBudgetItemDTOList(List<BudgetItem> budgetItems) {
		var budgetItemsDTO = new ArrayList<BudgetItemDTO>();

		budgetItems.forEach(budgetItem -> budgetItemsDTO.add(toBudgetItemDTO(budgetItem)));
		return budgetItemsDTO;
	}
}

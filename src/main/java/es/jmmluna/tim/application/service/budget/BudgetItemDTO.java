package es.jmmluna.tim.application.service.budget;

import java.util.Date;
import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.domain.model.Price;
import lombok.Data;

@Data
public class BudgetItemDTO extends DTO {
	private UUID budgetId;
	private String description;
	private Integer amount;
	private Price price;

	public BudgetItemDTO() {

	}

	public BudgetItemDTO(UUID uuid, UUID budgetId, String description, Integer amount, Price price) {
		this.uuid = uuid;
		this.budgetId = budgetId;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}

	public BudgetItemDTO(UUID uuid, UUID budgetId, String description, Integer amount, Price price,
			Date expirationDate) {
		this(uuid, budgetId, description, amount, price);
		this.setExpirationDate(expirationDate);
	}
}

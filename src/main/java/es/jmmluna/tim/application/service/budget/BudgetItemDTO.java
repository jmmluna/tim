package es.jmmluna.tim.application.service.budget;

import java.util.Date;
import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class BudgetItemDTO extends DTO {
//	private UUID budgetId;
	private String description;
	private Integer amount;
	private Double price;

	public BudgetItemDTO() {

	}

	public BudgetItemDTO(UUID uuid, String description, Integer amount, Double price) {
		this.uuid = uuid;
//		this.budgetId = budgetId;
		this.description = description;
		this.amount = amount;
		this.price = price;
	}

	public BudgetItemDTO(UUID uuid, String description, Integer amount, Double price,
			Date expirationDate) {
		this(uuid, description, amount, price);
		this.setExpirationDate(expirationDate);
	}
}

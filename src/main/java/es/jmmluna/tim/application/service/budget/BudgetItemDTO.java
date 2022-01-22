package es.jmmluna.tim.application.service.budget;

import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.Util;
import lombok.Data;

@Data
public class BudgetItemDTO extends DTO {
	private UUID budgetId;
	private String description;
	private Integer quantity;
	private Double price;

	public BudgetItemDTO() {

	}

	public BudgetItemDTO(UUID uuid, String description, Integer quantity, Double price) {
		this.uuid = uuid;
		this.description = description;
		this.quantity = quantity;
		setPrice(price);
	}
	
	public void setPrice(Double price) {
		this.price = Util.to2Decimal(price);
	}
	
	public Double getCost() {
		return  Util.to2Decimal(price * quantity);
	}
}

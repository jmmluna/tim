package es.jmmluna.tim.application.service.budget;

import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class BudgetItemDTO extends DTO {
	private String description;
	private Integer quantity;
	private Double price;

	public BudgetItemDTO() {

	}

	public BudgetItemDTO(UUID uuid, String description, Integer quantity, Double price) {
		this.uuid = uuid;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}

//	public BudgetItemDTO(UUID uuid, String description, Integer quantity, Double price,
//			Date expirationDate) {
//		this(uuid, description, quantity, price);
//		this.setExpirationDate(expirationDate);
//	}
}

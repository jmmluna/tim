package es.jmmluna.tim.application.service.budget;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class BudgetDTO extends DTO {
	private UUID customerId;
	private String description;
	private Integer budgetNumber;
	private Integer year;
	private Date date;
	private Double cost = 0.0;
	private List<BudgetItemDTO> budgetItems;

	public BudgetDTO() {

	}

	public BudgetDTO(UUID uuid, UUID customerId, String description, Integer budgetNumber, Integer year, Date date, List<BudgetItemDTO> budgetItems) {
		this.uuid = uuid;
		this.customerId = customerId;
		this.description = description;
		this.budgetNumber = budgetNumber;
		this.year = year;
		this.date = date;
		this.budgetItems = budgetItems;
	}

	public BudgetDTO(UUID uuid, UUID customerId, String description, Integer budgetNumber, Integer year, Date date, List<BudgetItemDTO> budgetItems,
			Date expirationDate) {
		this(uuid, customerId, description, budgetNumber, year, date, budgetItems);
		this.setExpirationDate(expirationDate);
	}
}

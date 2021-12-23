package es.jmmluna.tim.application.service.budget;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class BudgetDTO extends DTO {
	private String description;
	private Long budgetNumber;
	private Integer year;
	private Date date;
	private List<BudgetItemDTO> budgetItems;

	public BudgetDTO() {

	}

	public BudgetDTO(UUID uuid, String description, Long budgetNumber, Integer year, Date date, List<BudgetItemDTO> budgetItems) {
		this.uuid = uuid;
		this.description = description;
		this.budgetNumber = budgetNumber;
		this.year = year;
		this.date = date;
		this.budgetItems = budgetItems;
	}

	public BudgetDTO(UUID uuid, String description, Long budgetNumber, Integer year, Date date, List<BudgetItemDTO> budgetItems,
			Date expirationDate) {
		this(uuid, description, budgetNumber, year, date, budgetItems);
		this.setExpirationDate(expirationDate);
	}
}

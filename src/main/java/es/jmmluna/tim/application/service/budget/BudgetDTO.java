package es.jmmluna.tim.application.service.budget;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class BudgetDTO extends DTO {
	private UUID customerId;
	private String description;
	private Integer budgetNumber;
	private Integer year;
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date date;
	private Double cost = 0.0;
	private List<BudgetItemDTO> budgetItems;

	public BudgetDTO() {
		this.budgetItems = new ArrayList<BudgetItemDTO>();
	}

	public BudgetDTO(UUID uuid, UUID customerId, String description, Integer budgetNumber, Integer year, Date date, Double cost, List<BudgetItemDTO> budgetItems) {
		this.uuid = uuid;
		this.customerId = customerId;
		this.description = description;
		this.budgetNumber = budgetNumber;
		this.year = year;
		this.date = date;
		this.cost =cost;
		this.budgetItems = budgetItems;
	}

	public BudgetDTO(UUID uuid, UUID customerId, String description, Integer budgetNumber, Integer year, Date date, Double cost, List<BudgetItemDTO> budgetItems,
			Date expirationDate) {
		this(uuid, customerId, description, budgetNumber, year, date, cost, budgetItems);
		this.setExpirationDate(expirationDate);
	}
	
	public void add(BudgetItemDTO budgetItemDTO) {
		budgetItems.add(budgetItemDTO);
	}
}

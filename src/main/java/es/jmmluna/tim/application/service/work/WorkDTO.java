package es.jmmluna.tim.application.service.work;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.Util;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import lombok.Data;

@Data
public class WorkDTO extends DTO {
	private BudgetDTO budgetDTO;
	private CustomerDTO customerDTO;
	private String description;
	private Integer workStatus;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date date;
	private Double cost = 0.0;
	private List<WorkItemDTO> workItems;

	public WorkDTO() {
		this.workItems = new ArrayList<>();
	}

	public WorkDTO(UUID uuid, BudgetDTO budgetDTO, CustomerDTO customerDTO, String description, Integer workStatus,
			Date date, Double cost, List<WorkItemDTO> workItems) {
		this.uuid = uuid;
		this.customerDTO = customerDTO;
		this.budgetDTO = budgetDTO;
		this.description = description;
		this.workStatus = workStatus;
		this.date = date;
		setCost(cost);
		this.workItems = workItems;
	}

	public WorkDTO(UUID uuid, BudgetDTO budgetDTO, CustomerDTO customerDTO, String description, Integer workStatus,
			Date date, Double cost, List<WorkItemDTO> budgetItems, Date expirationDate) {
		this(uuid, budgetDTO, customerDTO, description, workStatus, date, cost, budgetItems);
		this.setExpirationDate(expirationDate);
	}

	public void setCost(Double cost) {
		this.cost = Util.to2Decimal(cost);
	}

	public void add(WorkItemDTO workItemDTO) {
		workItems.add(workItemDTO);
	}

	public void remove(Integer index) {
		workItems.remove(index.intValue());
	}

	public String getStatusDescription() {
		return this.workStatus == 0 ? "INICIADO" : "FINALIZADO";
	}
}

package es.jmmluna.tim.domain.model.budget;

import java.util.Date;
import java.util.List;

public class Budget {
	private BudgetId budgetId;
	private Long budgetNumber;
	private String description;
	private Integer year;
	private Date date;	
	private List<BudgetItem> budgetItems;
	private Date expirationDate;

	public Budget(BudgetId budgetId, Long budgetNumber, String description, Integer year, Date date,
			List<BudgetItem> budgetItems) {
		this.budgetId = budgetId;
		this.budgetNumber = budgetNumber;
		this.description = description;
		this.year = year;
		this.date = date;
		this.budgetItems = budgetItems;
	}

	public Budget(BudgetId budgetId, Long budgetNumber, String description, Integer year, Date date,
			List<BudgetItem> budgetItems, Date expirationDate) {
		this(budgetId, budgetNumber, description, year, date, budgetItems);
		this.expirationDate = expirationDate;
	}

	public BudgetId getBudgetId() {
		return budgetId;
	}

	public Long getBudgetNumber() {
		return budgetNumber;
	}

	public String getDescription() {
		return description;
	}

	public Integer getYear() {
		return year;
	}

	public Date getDate() {
		return date;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public List<BudgetItem> getBudgetItems() {
		return budgetItems;
	}

	public Double getTotal() {
		return 0D;
	}

	public void addBudgetItem(BudgetItem budgetItem) {

	}
}

package es.jmmluna.tim.domain.model.budget;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import es.jmmluna.tim.domain.model.Price;

public class Budget {
	private BudgetId budgetId;
	private Integer budgetNumber;
	private String description;
	private Integer year;
	private Date date;
	private final List<BudgetItem> budgetItems;
	private Date expirationDate;
	private Price totalCost;

	public Budget(BudgetId budgetId, Integer budgetNumber, String description, Integer year, Date date,
			List<BudgetItem> budgetItems) {
		this.budgetId = budgetId;
		this.budgetNumber = budgetNumber;
		this.description = description;
		this.year = year;
		this.date = date;
		this.budgetItems = new ArrayList<>(budgetItems);
		
//		totalCost = calculateTotalCost();
	}

	public Budget(BudgetId budgetId, Integer budgetNumber, String description, Integer year, Date date,
			List<BudgetItem> budgetItems, Date expirationDate) {
		this(budgetId, budgetNumber, description, year, date, budgetItems);
		this.expirationDate = expirationDate;
	}

	public BudgetId getBudgetId() {
		return budgetId;
	}

	public Integer getBudgetNumber() {
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
		//return Collections.unmodifiableList(budgetItems);
		return budgetItems;
	}

	public Boolean isActive() {
		return expirationDate == null;
	}

	public void activate() {
		this.expirationDate = null;
	}

	public void deactivate() {
		this.expirationDate = new Date();
	}

	public Price getTotalCost() {
		return totalCost;
	}

	public void add(BudgetItem budgetItem) {
		budgetItems.add(budgetItem);
//		totalCost = totalCost.plus(budgetItem.getCost());
	}

	private Price calculateTotalCost() {
		if(budgetItems.isEmpty())
			return Price.of(0.0);
		return budgetItems.stream().map(BudgetItem::getCost).reduce(Price::plus).get();

	}
}

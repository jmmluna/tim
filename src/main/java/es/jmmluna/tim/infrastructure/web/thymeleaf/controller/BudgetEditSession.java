package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.util.List;
import java.util.UUID;

import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import lombok.Data;

@Data
public class BudgetEditSession {
	private UUID budgetId;
	private UUID customerId;
	private String description;
	
	private BudgetItemDTO newBudgetItem;
	
}

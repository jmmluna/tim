package es.jmmluna.tim.infrastructure.web.thymeleaf.controller.dashboard;

import lombok.Data;

@Data
public class Dashboard {

	private Long employeeTotal;
	private Long customerTotal;
	private Long constructionMaterialTotal;
	private Long budgetTotal;
	private Long workTotal;
	private Long finalizedWorkTotal;
	private Long initiatedWorkTotal;
	private Long invoiceTotal;
}

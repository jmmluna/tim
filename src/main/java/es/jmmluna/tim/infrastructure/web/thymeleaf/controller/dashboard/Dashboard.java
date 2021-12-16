package es.jmmluna.tim.infrastructure.web.thymeleaf.controller.dashboard;

import lombok.Data;

@Data
public class Dashboard {

	private Long employeeTotal;
	private Long customerTotal;
	private Long constructionMaterialTotal;

//	public Long getEmployeesTotal() {
//		return employeesTotal;
//	}
//
//	public void setEmployeesTotal(Long employeesTotal) {
//		this.employeesTotal = employeesTotal;
//	}
}

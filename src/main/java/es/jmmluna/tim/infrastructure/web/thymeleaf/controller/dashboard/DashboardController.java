package es.jmmluna.tim.infrastructure.web.thymeleaf.controller.dashboard;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialCountService;
import es.jmmluna.tim.application.service.customer.useCase.GetActiveCustomerCount;
import es.jmmluna.tim.application.service.employee.EmployeeCountService;

@Controller
@RequestMapping("/")
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private EmployeeCountService employeeCountService;
	@Autowired
	private GetActiveCustomerCount customerCountService;
	@Autowired
	private ConstructionMaterialCountService constructionMaterialCountService;
	@GetMapping
	public String home(Model model) {
		Dashboard dashboard = new Dashboard();
		dashboard.setEmployeeTotal(employeeCountService.execute());
		dashboard.setCustomerTotal(customerCountService.execute());
		dashboard.setConstructionMaterialTotal(constructionMaterialCountService.execute());
		
		model.addAttribute("isDashboard",true);		
		model.addAttribute("dashboard", dashboard);						

		return "dashboard";
	}
}

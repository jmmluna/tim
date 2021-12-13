package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.customer.CustomerCountService;
import es.jmmluna.tim.application.service.employee.EmployeeCountService;
import es.jmmluna.tim.domain.model.dashboard.Dashboard;

@Controller
@RequestMapping("/")
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private EmployeeCountService employeeCountService;
	@Autowired
	private CustomerCountService customerCountService;
	
	@GetMapping
	public String home(Model model) {
		Dashboard dashboard = new Dashboard();
		dashboard.setEmployeeTotal(employeeCountService.execute());
		dashboard.setCustomerTotal(customerCountService.execute());
		
		model.addAttribute("isDashboard",true);		
		model.addAttribute("dashboard", dashboard);						

		return "dashboard";
	}
}

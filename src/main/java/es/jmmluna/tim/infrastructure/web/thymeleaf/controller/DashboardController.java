package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.employee.EmployeeCountService;
import es.jmmluna.tim.domain.model.dashboard.Dashboard;
import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/")
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private EmployeeCountService employeeCountService;
	
	@GetMapping
	public String home(Model model) {
		Dashboard dashboard = new Dashboard();
		dashboard.setEmployeesTotal(employeeCountService.execute());
		
		model.addAttribute("isDashboard",true);		
		model.addAttribute("dashboard", dashboard);						

		return "dashboard";
	}
}

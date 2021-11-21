package es.jmmluna.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.model.Dashboard;
import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/")
public class DashboardController {
	private static final Logger LOG = LoggerFactory.getLogger(DashboardController.class);
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping
	public String home(Model model) {
		Dashboard dashboard = new Dashboard();
		dashboard.setEmployeesTotal(employeeService.getCount());
		
		model.addAttribute("isDashboard",true);		
		model.addAttribute("dashboard", dashboard);						

		return "dashboard";
	}
}

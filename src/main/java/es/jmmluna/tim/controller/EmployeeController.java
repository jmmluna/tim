package es.jmmluna.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	@GetMapping
	public String getEmployees(Model model) {
		model.addAttribute("path","employees");
		model.addAttribute("employees", employeeService.getAll());					
		return "employee/employeeList";
	}
}

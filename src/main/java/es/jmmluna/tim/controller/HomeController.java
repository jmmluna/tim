package es.jmmluna.tim.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/")
public class HomeController {
	private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	private EmployeeService employeeService;
	@GetMapping
	public String home(Model model) {
		model.addAttribute("employees", employeeService.getAll());		
		
		List<Employee> employees = employeeService.getAll();
		
//		for(Employee employee: employees) {
//			LOG.info("empleados: " + employee.getId());
//			LOG.info("empleados: " + employee.getName());
//		}
//		LOG.info("empleados: " + employees.size());
		return "home";
	}
}

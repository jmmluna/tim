package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.employee.EmployeeListingService;
import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.useCase.CreateEmployeeHour;
import es.jmmluna.tim.application.service.employee.hour.useCase.DeleteEmployeeHour;
import es.jmmluna.tim.application.service.employee.hour.useCase.GetEmployeeHour;
import es.jmmluna.tim.application.service.employee.hour.useCase.GetEmployeeHourList;
import es.jmmluna.tim.application.service.employee.hour.useCase.GetEmployeeHourSummary;
import es.jmmluna.tim.application.service.employee.hour.useCase.GetEmployeeWorkHourSummary;
import es.jmmluna.tim.application.service.employee.hour.useCase.UpdateEmployeeHour;
import es.jmmluna.tim.application.service.work.useCase.GetWorkList;
import es.jmmluna.tim.domain.model.work.WorkStatus;

@Controller
@RequestMapping("/employees/hours")
public class EmployeeHourController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeHourController.class);

	@Autowired
	private GetEmployeeHour getEmployeeHour;
	
	@Autowired
	private GetEmployeeHourList getEmployeeHourList; 
	
	@Autowired
	private EmployeeListingService getEmployeeList;
	
	@Autowired
	private CreateEmployeeHour createEmployeeHour;
	
	@Autowired
	private UpdateEmployeeHour updateEmployeeHour;
	
	@Autowired
	private GetWorkList getWorkList;
	
	@Autowired
	private DeleteEmployeeHour deleteEmployeeHour;
	
	@Autowired
	private GetEmployeeHourSummary getEmployeeHourSummary;
	
	@Autowired
	private GetEmployeeWorkHourSummary getEmployeeWorkHourSummary;
	
	@GetMapping("/list")
	public String getHours(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeHourList", true);
		model.addAttribute("isEmployeeHourAllList", true);
		model.addAttribute("employeeHours", getEmployeeHourList.execute(WorkStatus.ALL));
		return "employee/employee-hours-list";
	}

	@GetMapping("/list/{filter}")
	public String getEmployeesFilter(@PathVariable("filter") String filter, Model model) {
		List<EmployeeHourDTO> employeeHours = new ArrayList<>();
		switch (filter) {

		case "initiated":
			employeeHours = getEmployeeHourList.execute(WorkStatus.INITIATED);
			model.addAttribute("isInitiatedWorkEmployeeHourList", true);
			break;
		case "finalized":
			employeeHours = getEmployeeHourList.execute(WorkStatus.FINALIZED);
			model.addAttribute("isFinalizedWorkEmployeeHourList", true);
			break;
		}

		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeHourList", true);
		model.addAttribute("employeeHours", employeeHours);

		return "employee/employee-hours-list";
	}
	
	@GetMapping("/save")
	public String create(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isAddEmployeeHour", true);
		model.addAttribute("employees", getEmployeeList.execute(EElementList.ACTIVE));
		model.addAttribute("works", getWorkList.execute(EElementList.ACTIVE));
		model.addAttribute("employeeHour", new EmployeeHourDTO());

		return "employee/employee-hours-save";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEditEmployeeHour", true);				
		model.addAttribute("employees", getEmployeeList.execute(EElementList.ACTIVE));
		model.addAttribute("works", getWorkList.execute(EElementList.ACTIVE));
		model.addAttribute("employeeHour", getEmployeeHour.execute(UUID.fromString(uuid)));
		
		return "employee/employee-hours-save";
	}

	@PostMapping("save")
	public String save(EmployeeHourDTO employeeHour, BindingResult result, Model model) {
		
		if(employeeHour.getUuid() == null)
			createEmployeeHour.execute(employeeHour);
		else
			updateEmployeeHour.execute(employeeHour);
		return "redirect:/employees/hours/list/initiated";
	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
		
		deleteEmployeeHour.execute(UUID.fromString(uuid));

		return "redirect:/employees/hours/list/initiated";
	}

	@GetMapping("/summary")
	public String getSummary(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeHourSummary", true);		
		model.addAttribute("employeeHours", getEmployeeHourSummary.execute());
		return "employee/employee-hours-summary";
	}
	
	@GetMapping("/work/summary")
	public String getWorkSummary(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeWorkHourSummary", true);		
		model.addAttribute("employeeHours", getEmployeeWorkHourSummary.execute());
		return "employee/employee-work-hours-summary";
	}
}

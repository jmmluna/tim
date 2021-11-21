package es.jmmluna.tim.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping("list")
	public String getEmployees(Model model) {
		model.addAttribute("isEmployees",true);
		model.addAttribute("isEmployeeList",true);
		model.addAttribute("employees", employeeService.getAll());					
		return "employee/employee-list";
	}
	
	@GetMapping("add/form")
	public String formAddEmployee(Model model) {
		model.addAttribute("isEmployees",true);
		model.addAttribute("isAddEmployee",true);
		 model.addAttribute("employee", new Employee()); 
							
		return "employee/employee-add";
	}
	
	@PostMapping("add")
    public String addEmployee( Employee employee, BindingResult result, Model model) {
        if (result.hasErrors()) {
        	return "employee/employee-add";
        }

        employeeService.save(employee);
        return "redirect:/employees/list";
    }
	
//	@GetMapping("edit/{id}")
//    public String showUpdateForm(@PathVariable("id") long id, Model model) {
//        Student student = studentRepository.findById(id)
//            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        model.addAttribute("student", student);
//        return "update-student";
//    }
//
//    @PostMapping("update/{id}")
//    public String updateStudent(@PathVariable("id") long id, @Valid Student student, BindingResult result,
//        Model model) {
//        if (result.hasErrors()) {
//            student.setId(id);
//            return "update-student";
//        }
//
//        studentRepository.save(student);
//        model.addAttribute("students", studentRepository.findAll());
//        return "index";
//    }
//
//    @GetMapping("delete/{id}")
//    public String deleteStudent(@PathVariable("id") long id, Model model) {
//        Student student = studentRepository.findById(id)
//            .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + id));
//        studentRepository.delete(student);
//        model.addAttribute("students", studentRepository.findAll());
//        return "index";
//    }
}

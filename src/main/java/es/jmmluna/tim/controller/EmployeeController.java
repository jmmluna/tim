package es.jmmluna.tim.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import es.jmmluna.tim.model.Employee;
import es.jmmluna.tim.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private SpringTemplateEngine templateEngine;

	@GetMapping("/list")
	public String getEmployees(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeList", true);
		model.addAttribute("isAllEmployeeList", true);
		model.addAttribute("employees", employeeService.getAll());
		return "employee/employee-list";
	}
	
	@GetMapping("/list/{filter}")
	public String getEmployeesFilter(@PathVariable("filter") String filter, Model model) {
		List<Employee> employees = new ArrayList<Employee>();
		switch(filter) {
		
		case "actives":
			employees = employeeService.getActives();
			model.addAttribute("isActiveEmployeeList", true);
			break;
		case "inactives":
			employees = employeeService.getInactives();
			model.addAttribute("isInactiveEmployeeList", true);
			break;	
		}
		
		
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEmployeeList", true);
		model.addAttribute("employees", employees);

		return "employee/employee-list";
	}


	@GetMapping("/save/{id}")
	public String edit(@PathVariable("id") Long id, Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isEditEmployee", true);
		model.addAttribute("employee", id != null && id != 0 ? employeeService.get(id) : new Employee());
		return "employee/employee-save";
	}

	@GetMapping("/save")
	public String create(Model model) {
		model.addAttribute("isEmployees", true);
		model.addAttribute("isAddEmployee", true);
		model.addAttribute("employee", new Employee());

		return "employee/employee-save";
	}

	@PostMapping("save")
	public String save(Employee employee, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "employee/employee-save";
		}

		employeeService.save(employee);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/delete/{id}")
	 public String delete(@PathVariable Long id, Model model) {
		employeeService.delete(id);
	 
	  return "redirect:/employees/list";
	 }
	

	@GetMapping("print")
	public ResponseEntity<ByteArrayResource> print(Model model) {

		ByteArrayOutputStream byteArrayOutputStreamPDF = generatePdfFromHtml(parseThymeleafTemplate());
		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());

		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=informe.pdf")
				.contentType(MediaType.APPLICATION_PDF).contentLength(inputStreamResourcePDF.contentLength())
				.body(inputStreamResourcePDF);

	}

	private String parseThymeleafTemplate() {
		Context context = new Context();
		context.setVariable("to", "Baeldung");

		return templateEngine.process("printTemplate", context);
	}

	public void generatePdfFromHtmlToFile(String html) {
		try {
			String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
			LOG.info("###### PDF Generado en: " + outputFolder);
			OutputStream outputStream = new FileOutputStream(outputFolder);

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html);
			renderer.layout();
			renderer.createPDF(outputStream);

			outputStream.close();
		} catch (IOException | DocumentException e) {

			throw new RuntimeException();
		}

	}

	public ByteArrayOutputStream generatePdfFromHtml(String html) {
		String urlBase = "http://localhost:9080";
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {

			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(html, urlBase);

			renderer.layout();
			renderer.createPDF(bos, false);
			renderer.finishPDF();
			LOG.info("PDF created correctamente");

			return bos;
		} catch (DocumentException e) {

			throw new RuntimeException();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					LOG.error("Error creando pdf", e);
				}
			}
		}

	}
}

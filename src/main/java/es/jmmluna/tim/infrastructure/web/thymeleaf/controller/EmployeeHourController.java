package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.employee.hour.EmployeeHourDTO;
import es.jmmluna.tim.application.service.employee.hour.useCase.GetEmployeeHourList;
import es.jmmluna.tim.domain.model.work.WorkStatus;

@Controller
@RequestMapping("/employees/hours")
public class EmployeeHourController {
	private static final Logger LOG = LoggerFactory.getLogger(EmployeeHourController.class);

	@Autowired
	private GetEmployeeHourList getEmployeeHourList; 

	
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
//
//	@GetMapping("/save/{id}")
//	public String edit(@PathVariable("id") Long id, Model model) {
//		model.addAttribute("isEmployees", true);
//		model.addAttribute("isEditEmployee", true);
//		model.addAttribute("employee", id != null && id != 0 ? employeeByIdService.execute(id) : new EmployeeDTO());
//		return "employee/employee-save";
//	}
//
//	@GetMapping("/save")
//	public String create(Model model) {
//		model.addAttribute("isEmployees", true);
//		model.addAttribute("isAddEmployee", true);
//		model.addAttribute("employee", new EmployeeDTO());
//
//		return "employee/employee-save";
//	}
//	
//	@GetMapping("/hours/list")
//	public String addHours(Model model) {
////		model.addAttribute("isEmployees", true);
////		model.addAttribute("isAddEmployee", true);
////		model.addAttribute("employee", new EmployeeDTO());
//
//		return "employee/employee-hours-list";
//	}
//
//	@PostMapping("save")
//	public String save(EmployeeDTO employee, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			LOG.error("####################" + result);
//			
//			return "employee/employee-save";
//		}
//
//		employeeSaveService.execute(employee);
//		return "redirect:/employees/list/actives";
//	}
//
//	@GetMapping("/delete/{id}")
//	public String delete(@PathVariable Long id, Model model) {
//		var employeeDTO = employeeByIdService.execute(id);
//		employeeDeletionService.execute(employeeDTO);
//
//		return "redirect:/employees/list/actives";
//	}
//
//	@GetMapping("print")
//	public ResponseEntity<ByteArrayResource> print(Model model) {
//
//		ByteArrayOutputStream byteArrayOutputStreamPDF = generatePdfFromHtml(parseThymeleafTemplate());
//		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
//
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=informe.pdf")
//				.contentType(MediaType.APPLICATION_PDF).contentLength(inputStreamResourcePDF.contentLength())
//				.body(inputStreamResourcePDF);
//
//	}
//
//	private String parseThymeleafTemplate() {
//		Context context = new Context();
//		context.setVariable("to", "Baeldung");
//
//		return templateEngine.process("printTemplate", context);
//	}
//
//	public void generatePdfFromHtmlToFile(String html) {
//		try {
//			String outputFolder = System.getProperty("user.home") + File.separator + "thymeleaf.pdf";
//			LOG.info("###### PDF Generado en: " + outputFolder);
//			OutputStream outputStream = new FileOutputStream(outputFolder);
//
//			ITextRenderer renderer = new ITextRenderer();
//			renderer.setDocumentFromString(html);
//			renderer.layout();
//			renderer.createPDF(outputStream);
//
//			outputStream.close();
//		} catch (IOException | DocumentException e) {
//
//			throw new RuntimeException();
//		}
//
//	}
//
//	public ByteArrayOutputStream generatePdfFromHtml(String html) {
//		String urlBase = "http://localhost:9080";
//		ByteArrayOutputStream bos = new ByteArrayOutputStream();
//		try {
//
//			ITextRenderer renderer = new ITextRenderer();
//			renderer.setDocumentFromString(html, urlBase);
//
//			renderer.layout();
//			renderer.createPDF(bos, false);
//			renderer.finishPDF();
//			LOG.info("PDF created correctamente");
//
//			return bos;
//		} catch (DocumentException e) {
//
//			throw new RuntimeException();
//		} finally {
//			if (bos != null) {
//				try {
//					bos.close();
//				} catch (IOException e) {
//					LOG.error("Error creando pdf", e);
//				}
//			}
//		}
//
//	}
}

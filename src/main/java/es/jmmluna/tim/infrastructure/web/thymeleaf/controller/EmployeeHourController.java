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

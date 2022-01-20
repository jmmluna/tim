package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.useCase.GetBudget;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialListingService;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomerList;
import es.jmmluna.tim.application.service.work.WorkDTO;
import es.jmmluna.tim.application.service.work.WorkItemDTO;
import es.jmmluna.tim.application.service.work.useCase.CreateWork;
import es.jmmluna.tim.application.service.work.useCase.DisableWork;
import es.jmmluna.tim.application.service.work.useCase.GetWork;
import es.jmmluna.tim.application.service.work.useCase.GetWorkList;
import es.jmmluna.tim.application.service.work.useCase.UpdateWork;
import es.jmmluna.tim.infrastructure.web.thymeleaf.ReportGenerator;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/works")
@Slf4j
public class WorkController {
	private static String NO_UUID = null;
	@Autowired
	private CreateWork createWork;

	@Autowired
	private UpdateWork updateWork;

	@Autowired
	private DisableWork disableWork;

	@Autowired
	private GetWorkList getWorkList;

	@Autowired
	private GetWork getWork;

	@Autowired
	private GetCustomerList getCustomerList;
	
	@Autowired
	private GetCustomer getCustomer;
	
	@Autowired
	private GetBudget getBudget;
	
	@Autowired
	private ReportGenerator reportGenerator;
	
	@Autowired
	private ConstructionMaterialListingService constructionMaterialListingService;
	


	private WorkDTO workDTOForAddItem;

	@GetMapping("/list")
	public String getWorks(Model model) {
		this.workDTOForAddItem = null;
		model.addAttribute("isWorks", true);
		model.addAttribute("isWorkList", true);
		model.addAttribute("isAllWorkList", true);
		model.addAttribute("works", getWorkList.execute(EElementList.ALL));
		return "work/work-list";
	}

	@GetMapping("/list/{filter}")
	public String getWorkilter(@PathVariable("filter") String filter, Model model) {
		this.workDTOForAddItem = null;
		List<WorkDTO> works = new ArrayList<>();
		switch (filter) {

		case "actives":
			works = getWorkList.execute(EElementList.ACTIVE);
			model.addAttribute("isActiveWorkList", true);
			break;
		case "inactives":
			works = getWorkList.execute(EElementList.INACTIVE);
			model.addAttribute("isInactiveWorkList", true);
			break;
		}

		model.addAttribute("isWorks", true);
		model.addAttribute("isWorkList", true);
		model.addAttribute("works", works);

		return "work/work-list";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isWorks", true);
		model.addAttribute("isEditWork", true);
		model.addAttribute("customers", getCustomerList.execute(EElementList.ACTIVE));
		model.addAttribute("constructionMaterials", constructionMaterialListingService.execute(EElementList.ACTIVE));
		model.addAttribute("workItem", new WorkItemDTO());
				
		checkAddItem(model, uuid);		
		return "work/work-save";
	}

	@GetMapping("/create")
	public RedirectView createFromBudget(Model model, @RequestParam(value = "budgetId", required = true) String budgetId) {
		var budgetDTO = getBudget.execute(UUID.fromString(budgetId));
		createWork.execute(budgetDTO);

		
		return new RedirectView("/works/list/actives", true);
	}

	@PostMapping("item/add")
	public RedirectView addWorkItem(String uuidSelectedCustomer, String selectedBudgetId, String selectedWorkDescription, WorkItemDTO workItem, RedirectAttributes redirectAttributes) {
						
		var workId = workItem.getWorkId();
		
		if (this.workDTOForAddItem == null)
			this.workDTOForAddItem = workId!=null? getWork.execute(workId): new WorkDTO();

		this.workDTOForAddItem.add(workItem);
		this.workDTOForAddItem.setDescription(selectedWorkDescription);
		this.workDTOForAddItem.setCustomerDTO(getCustomer.execute(UUID.fromString(uuidSelectedCustomer)));
		this.workDTOForAddItem.setBudgetDTO(getBudget.execute(UUID.fromString(selectedBudgetId)));
		

		if(workId !=null)
		return new RedirectView("/works/save/" + workId, true);
		else 
			return new RedirectView("/works/save", true);
	}
	

	@GetMapping("item/delete")	
	public RedirectView deleteWorkItem(@RequestParam(value = "itemIndex", required = true) Integer itemIndex, @RequestParam(value = "workId", required = true) String workId,  Model model, RedirectAttributes redirectAttributes) {
		
		if (this.workDTOForAddItem == null)
			this.workDTOForAddItem = workId!=null? getWork.execute(UUID.fromString(workId)): new WorkDTO();

		this.workDTOForAddItem.remove(itemIndex);

		
//		String message = "Nuevo elemento eliminado!! " + itemIndex +  " " + workId;
//		redirectAttributes.addFlashAttribute("budgetItemMessage", message);
		
		if(workId !=null)
			return new RedirectView("/works/save/" + workId, true);
			else 
				return new RedirectView("/works/save", true);
	}

	@PostMapping("save")
	public String save(WorkDTO work, BindingResult result, Model model) {
		if (result.hasErrors()) {			
			return "work/work-save";
		}

		if (work.getUuid() == null)
			createWork.execute(work);
		else 
			updateWork.execute(work);		

		return "redirect:/works/list/actives";
	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
		disableWork.execute(UUID.fromString(uuid));

		return "redirect:/works/list/actives";
	}
	
//	@GetMapping("print/{uuid}")
//	public ResponseEntity<ByteArrayResource> print(@PathVariable String uuid, Model model) {
//		
//		var budget = getBudget.execute(UUID.fromString(uuid));
//		ByteArrayOutputStream byteArrayOutputStreamPDF = reportGenerator.generatePdfFromHtml("work/budget-report", budget, "budget");
//		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
//		String fileName = "tim-presupuesto-" + budget.getBudgetNumber() + ".pdf";
//		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
//				.contentType(MediaType.APPLICATION_PDF).contentLength(inputStreamResourcePDF.contentLength())
//				.body(inputStreamResourcePDF);
//
//	}
	
	private void checkAddItem(Model model, String uuid) {
		if (workDTOForAddItem == null)
			model.addAttribute("work",
					uuid != null && !uuid.isEmpty() ? getWork.execute(UUID.fromString(uuid)) : new WorkDTO());
		else
			model.addAttribute("work", workDTOForAddItem);
	}
}

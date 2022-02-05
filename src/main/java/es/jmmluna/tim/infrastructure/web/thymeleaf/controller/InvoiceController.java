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
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialListingService;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomerList;
import es.jmmluna.tim.application.service.employee.EmployeeListingService;
import es.jmmluna.tim.application.service.invoice.InvoiceDTO;
import es.jmmluna.tim.application.service.invoice.InvoiceItemDTO;
import es.jmmluna.tim.application.service.invoice.useCase.CreateInvoice;
import es.jmmluna.tim.application.service.invoice.useCase.DisableInvoice;
import es.jmmluna.tim.application.service.invoice.useCase.GetInvoice;
import es.jmmluna.tim.application.service.invoice.useCase.GetInvoiceList;
import es.jmmluna.tim.application.service.invoice.useCase.UpdateInvoice;
import es.jmmluna.tim.infrastructure.web.thymeleaf.ReportGenerator;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/invoices")
@Slf4j
public class InvoiceController {
	private static String NO_UUID = null;
	@Autowired
	private CreateInvoice createInvoice;

	@Autowired
	private UpdateInvoice updateInvoice;

	@Autowired
	private DisableInvoice disableInvoice;

	@Autowired
	private GetInvoiceList getInvoiceList;

	@Autowired
	private GetInvoice getInvoice;

	@Autowired
	private GetCustomerList getCustomerList;
	
	@Autowired
	private EmployeeListingService getEmployeeList;
	
	@Autowired
	private GetCustomer getCustomer;
	
	@Autowired
	private ReportGenerator reportGenerator;
	
	@Autowired
	private ConstructionMaterialListingService constructionMaterialListingService;
	


	private InvoiceDTO invoiceDTOForAddItem;

	@GetMapping("/list")
	public String getInvoices(Model model) {
		this.invoiceDTOForAddItem = null;
		model.addAttribute("isInvoices", true);
		model.addAttribute("isInvoiceList", true);
		model.addAttribute("isAllInvoiceList", true);
		model.addAttribute("invoices", getInvoiceList.execute(EElementList.ALL));
		return "invoice/invoice-list";
	}

	@GetMapping("/list/{filter}")
	public String getInvoiceilter(@PathVariable("filter") String filter, Model model) {
		this.invoiceDTOForAddItem = null;
		List<InvoiceDTO> invoices = new ArrayList<>();
		switch (filter) {

		case "actives":
			invoices = getInvoiceList.execute(EElementList.ACTIVE);
			model.addAttribute("isActiveInvoiceList", true);
			break;
		case "inactives":
			invoices = getInvoiceList.execute(EElementList.INACTIVE);
			model.addAttribute("isInactiveInvoiceList", true);
			break;
		}

		model.addAttribute("isInvoices", true);
		model.addAttribute("isInvoiceList", true);
		model.addAttribute("invoices", invoices);

		return "invoice/invoice-list";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isInvoices", true);
		model.addAttribute("isEditInvoice", true);
		model.addAttribute("customers", getCustomerList.execute(EElementList.ACTIVE));
		model.addAttribute("constructionMaterials", constructionMaterialListingService.execute(EElementList.ACTIVE));
		model.addAttribute("employees", getEmployeeList.execute(EElementList.ACTIVE));
		model.addAttribute("invoiceItem", new InvoiceItemDTO());
		
		
		checkAddItem(model, uuid);
		
		return "invoice/invoice-save";
	}

//	@GetMapping("/save")
//	public String create(Model model) {
//		model.addAttribute("isBudgets", true);
//		model.addAttribute("isAddBudget", true);
//		model.addAttribute("customers", getCustomerList.execute(EElementList.ACTIVE));
//		model.addAttribute("constructionMaterials", constructionMaterialListingService.execute(EElementList.ACTIVE));
//		model.addAttribute("employees", getEmployeeList.execute(EElementList.ACTIVE));
//		model.addAttribute("budgetItem", new BudgetItemDTO());
//		
//		checkAddItem(model, NO_UUID);
//
//		return "budget/budget-save";
//	}

	@PostMapping("item/add")
	public RedirectView addBudgetItem(String uuidSelectedCustomer, String selectedInvoiceDescription, InvoiceItemDTO invoiceItem, RedirectAttributes redirectAttributes) {
						
		if (this.invoiceDTOForAddItem == null)
			this.invoiceDTOForAddItem = invoiceItem.getInvoiceId()!=null? getInvoice.execute(invoiceItem.getInvoiceId()): new InvoiceDTO();

		this.invoiceDTOForAddItem.add(invoiceItem);
		this.invoiceDTOForAddItem.setDescription(selectedInvoiceDescription);
				
		if(!uuidSelectedCustomer.isEmpty())
		this.invoiceDTOForAddItem.setCustomerDTO(getCustomer.execute(UUID.fromString(uuidSelectedCustomer)));
		
//		String message = "Nuevo elemento añadido!! " + selectedBudgetDescription + "  "+ uuidSelectedCustomer;
//		redirectAttributes.addFlashAttribute("budgetItemMessage", message);

		if(invoiceItem.getInvoiceId() !=null)
		return new RedirectView("/invoices/save/" + invoiceItem.getInvoiceId(), true);
		else 
			return new RedirectView("/invoices/save", true);
	}
	

	@GetMapping("item/delete")	
	public RedirectView deleteInvoiceItem(@RequestParam(value = "itemIndex", required = true) Integer itemIndex, @RequestParam(value = "invoiceId", required = true) String invoiceId,  Model model, RedirectAttributes redirectAttributes) {
		
		if (this.invoiceDTOForAddItem == null)
			this.invoiceDTOForAddItem = invoiceId!=null? getInvoice.execute(UUID.fromString(invoiceId)): new InvoiceDTO();

		this.invoiceDTOForAddItem.remove(itemIndex);

		
//		String message = "Nuevo elemento eliminado!! " + itemIndex +  " " + budgetId;
//		redirectAttributes.addFlashAttribute("budgetItemMessage", message);
		
		if(invoiceId !=null)
			return new RedirectView("/invoices/save/" + invoiceId, true);
			else 
				return new RedirectView("/invoices/save", true);
	}

	@PostMapping("save")
	public RedirectView save(InvoiceDTO invoice, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
				
		if (invoice.getUuid() == null) {			
			createInvoice.execute(invoice.getWorkDTO());
		}else 
			updateInvoice.execute(invoice);		
		
		

		return new RedirectView("/invoices/list/actives", true);

	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
		disableInvoice.execute(UUID.fromString(uuid));

		return "redirect:/invoices/list/actives";
	}
	
	@GetMapping("print/{uuid}")
	public ResponseEntity<ByteArrayResource> print(@PathVariable String uuid, Model model) {
		
		var invoice = getInvoice.execute(UUID.fromString(uuid));
		ByteArrayOutputStream byteArrayOutputStreamPDF = reportGenerator.generatePdfFromHtml("invoice/invoice-report", invoice, "invoice");
		ByteArrayResource inputStreamResourcePDF = new ByteArrayResource(byteArrayOutputStreamPDF.toByteArray());
		String fileName = "tim-factura-" + invoice.getInvoiceNumber() + ".pdf";
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
				.contentType(MediaType.APPLICATION_PDF).contentLength(inputStreamResourcePDF.contentLength())
				.body(inputStreamResourcePDF);

	}
	
	private void checkAddItem(Model model, String uuid) {
		if (invoiceDTOForAddItem == null)
			model.addAttribute("invoice",
					uuid != null && !uuid.isEmpty() ? getInvoice.execute(UUID.fromString(uuid)) : new InvoiceDTO());
		else
			model.addAttribute("invoice", invoiceDTOForAddItem);
	}
}

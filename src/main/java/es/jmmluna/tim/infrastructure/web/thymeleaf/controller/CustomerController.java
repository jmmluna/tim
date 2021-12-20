package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.useCase.DisableCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomerList;
import es.jmmluna.tim.application.service.customer.useCase.SaveCustomer;

@Controller
@RequestMapping("/customers")
public class CustomerController {
//	private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private SaveCustomer saveCustomer;

	@Autowired
	private DisableCustomer disableCustomer;

	@Autowired
	private GetCustomerList getCustomerList;

	@Autowired
	private GetCustomer getCustomer;

	@GetMapping("/list")
	public String getCustomers(Model model) {
		model.addAttribute("isCustomers", true);
		model.addAttribute("isCustomerList", true);
		model.addAttribute("isAllCustomerList", true);
		model.addAttribute("customers", getCustomerList.execute(EElementList.ALL));
		return "customer/customer-list";
	}

	@GetMapping("/list/{filter}")
	public String getCustomerFilter(@PathVariable("filter") String filter, Model model) {
		List<CustomerDTO> customers = new ArrayList<CustomerDTO>();
		switch (filter) {

		case "actives":
			customers = getCustomerList.execute(EElementList.ACTIVE);
			model.addAttribute("isActiveCustomerList", true);
			break;
		case "inactives":
			customers = getCustomerList.execute(EElementList.INACTIVE);
			model.addAttribute("isInactiveCustomerList", true);
			break;
		}

		model.addAttribute("isCustomers", true);
		model.addAttribute("isCustomerList", true);
		model.addAttribute("customers", customers);

		return "customer/customer-list";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isCustomers", true);
		model.addAttribute("isEditCustomer", true);
		model.addAttribute("customer", uuid != null && !uuid.isEmpty() ? getCustomer.execute(UUID.fromString(uuid)) : new CustomerDTO());
		return "customer/customer-save";
	}

	@GetMapping("/save")
	public String create(Model model) {
		model.addAttribute("isCustomers", true);
		model.addAttribute("isAddCustomer", true);
		model.addAttribute("customer", new CustomerDTO());

		return "customer/customer-save";
	}

	@PostMapping("save")
	public String save(CustomerDTO customer, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "customer/customer-save";
		}

		if(customer.getUuid() == null) customer.setUuid(UUID.randomUUID());
		saveCustomer.execute(customer);
		return "redirect:/customers/list/actives";
	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
//		var customerDTO = customerByIdService.execute(id);
//		customerDeletionService.execute(customerDTO);
		disableCustomer.execute(UUID.fromString(uuid));

		return "redirect:/customers/list/actives";
	}	
}

package es.jmmluna.tim.infrastructure.web.thymeleaf.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.application.service.budget.BudgetDTO;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.budget.useCase.CreateBudget;
import es.jmmluna.tim.application.service.budget.useCase.DisableBudget;
import es.jmmluna.tim.application.service.budget.useCase.GetBudget;
import es.jmmluna.tim.application.service.budget.useCase.GetBudgetList;
import es.jmmluna.tim.application.service.budget.useCase.UpdateBudget;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomer;
import es.jmmluna.tim.application.service.customer.useCase.GetCustomerList;
import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/budgets")
@Slf4j
public class BudgetController {
	private static String NO_UUID = null;
	@Autowired
	private CreateBudget createBudget;

	@Autowired
	private UpdateBudget updateBudget;

	@Autowired
	private DisableBudget disableBudget;

	@Autowired
	private GetBudgetList getBudgetList;

	@Autowired
	private GetBudget getBudget;

	@Autowired
	private GetCustomerList getCustomerList;
	
	@Autowired
	private GetCustomer getCustomer;

	private BudgetDTO budgetDTOForAddItem;

	@GetMapping("/list")
	public String getCustomers(Model model) {
		this.budgetDTOForAddItem = null;
		model.addAttribute("isBudgets", true);
		model.addAttribute("isBudgetList", true);
		model.addAttribute("isAllBudgetList", true);
		model.addAttribute("budgets", getBudgetList.execute(EElementList.ALL));
		return "budget/budget-list";
	}

	@GetMapping("/list/{filter}")
	public String getBudgetilter(@PathVariable("filter") String filter, Model model) {
		this.budgetDTOForAddItem = null;
		List<BudgetDTO> budgets = new ArrayList<BudgetDTO>();
		switch (filter) {

		case "actives":
			budgets = getBudgetList.execute(EElementList.ACTIVE);
			model.addAttribute("isActiveBudgetList", true);
			break;
		case "inactives":
			budgets = getBudgetList.execute(EElementList.INACTIVE);
			model.addAttribute("isInactiveBudgetList", true);
			break;
		}

		model.addAttribute("isBudgets", true);
		model.addAttribute("isBudgetList", true);
		model.addAttribute("budgets", budgets);

		return "budget/budget-list";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isBudgets", true);
		model.addAttribute("isEditBudget", true);
		model.addAttribute("customers", getCustomerList.execute(EElementList.ACTIVE));
		model.addAttribute("budgetItem", new BudgetItemDTO());
		
		
		checkAddItem(model, uuid);
//		if (budgetDTOForAddItem == null)
//			model.addAttribute("budget",
//					uuid != null && !uuid.isEmpty() ? getBudget.execute(UUID.fromString(uuid)) : new BudgetDTO());
//		else
//			model.addAttribute("budget", budgetDTOForAddItem);
		
		return "budget/budget-save";
	}

	@GetMapping("/save")
	public String create(Model model) {
		model.addAttribute("isBudgets", true);
		model.addAttribute("isAddBudget", true);
		model.addAttribute("customers", getCustomerList.execute(EElementList.ACTIVE));
		model.addAttribute("budgetItem", new BudgetItemDTO());
//		model.addAttribute("budget", new BudgetDTO());
		
		checkAddItem(model, NO_UUID);

		return "budget/budget-save";
	}

	@PostMapping("addItem")
	public RedirectView addBudgetItem(String uuidSelectedCustomer, String selectedBudgetDescription, BudgetItemDTO budgetItem, RedirectAttributes redirectAttributes) {
						
		if (this.budgetDTOForAddItem == null)
			this.budgetDTOForAddItem = budgetItem.getBudgetId()!=null? getBudget.execute(budgetItem.getBudgetId()): new BudgetDTO();

		this.budgetDTOForAddItem.add(budgetItem);
		this.budgetDTOForAddItem.setDescription(selectedBudgetDescription);
		this.budgetDTOForAddItem.setCustomerDTO(getCustomer.execute(UUID.fromString(uuidSelectedCustomer)));
		
//		String message = "Nuevo elemento añadido!! " + selectedBudgetDescription + "  "+ uuidSelectedCustomer;
//		redirectAttributes.addFlashAttribute("budgetItemMessage", message);

		if(budgetItem.getBudgetId() !=null)
		return new RedirectView("/budgets/save/" + budgetItem.getBudgetId(), true);
		else 
			return new RedirectView("/budgets/save", true);
	}

	@PostMapping("save")
	public String save(BudgetDTO budget, BindingResult result, Model model) {
		if (result.hasErrors()) {			
			return "budget/budget-save";
		}

		if (budget.getUuid() == null)
			createBudget.execute(budget);
		else
			updateBudget.execute(budget);

		return "redirect:/budgets/list/actives";
	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
		disableBudget.execute(UUID.fromString(uuid));

		return "redirect:/budgets/list/actives";
	}
	
	private void checkAddItem(Model model, String uuid) {
		if (budgetDTOForAddItem == null)
			model.addAttribute("budget",
					uuid != null && !uuid.isEmpty() ? getBudget.execute(UUID.fromString(uuid)) : new BudgetDTO());
		else
			model.addAttribute("budget", budgetDTOForAddItem);
	}
}

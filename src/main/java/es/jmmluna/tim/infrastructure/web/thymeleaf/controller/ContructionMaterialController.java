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
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialByIdService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialDTO;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialDeletionService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialListingService;
import es.jmmluna.tim.application.service.construction_material.ConstructionMaterialSaveService;

@Controller
@RequestMapping("/construction-materials")
public class ContructionMaterialController {
	private static final Logger LOG = LoggerFactory.getLogger(ContructionMaterialController.class);

	@Autowired
	private ConstructionMaterialSaveService constructionMaterialSaveService;

	@Autowired
	private ConstructionMaterialDeletionService constructionMaterialDeletionService;

	@Autowired
	private ConstructionMaterialListingService constructionMaterialListingService;

	@Autowired
	private ConstructionMaterialByIdService constructionMaterialByIdService;

	@GetMapping("/list")
	public String getConstructionMaterials(Model model) {
		model.addAttribute("isConstructionMaterial", true);
		model.addAttribute("isConstructionMaterialList", true);
		model.addAttribute("isAllConstructionMaterialList", true);
		model.addAttribute("constructionMaterials", constructionMaterialListingService.execute(EElementList.ALL));
		return "construction-material/construction-material-list";
	}

	@GetMapping("/list/{filter}")
	public String getConstructionMaterialFilter(@PathVariable("filter") String filter, Model model) {
		List<ConstructionMaterialDTO> constructionMaterials = new ArrayList<ConstructionMaterialDTO>();
		switch (filter) {

		case "actives":
			constructionMaterials = constructionMaterialListingService.execute(EElementList.ACTIVE);
			model.addAttribute("isActiveConstructionMaterialList", true);
			break;
		case "inactives":
			constructionMaterials = constructionMaterialListingService.execute(EElementList.INACTIVE);
			model.addAttribute("isInactiveConstructionMaterialList", true);
			break;
		}

		model.addAttribute("isConstructionMaterials", true);
		model.addAttribute("isConstructionMaterialList", true);
		model.addAttribute("constructionMaterials", constructionMaterials);

		return "construction-material/construction-material-list";
	}

	@GetMapping("/save/{uuid}")
	public String edit(@PathVariable("uuid") String uuid, Model model) {
		model.addAttribute("isConstructionMaterials", true);
		model.addAttribute("isEditConstructionMaterial", true);
		model.addAttribute("constructionMaterial", uuid != null && !uuid.isEmpty() ? constructionMaterialByIdService.execute(uuid) : new ConstructionMaterialDTO());
		return "construction-material/construction-material-save";
	}

	@GetMapping("/save")
	public String create(Model model) {
		model.addAttribute("isConstructionMaterials", true);
		model.addAttribute("isAddConstructionMaterial", true);
		model.addAttribute("constructionMaterial", new ConstructionMaterialDTO());

		return "construction-material/construction-material-save";
	}

	@PostMapping("save")
	public String save(ConstructionMaterialDTO constructionMaterial, BindingResult result, Model model) {
		if (result.hasErrors()) {
			LOG.error("####################" + result);
			
			return "construction-material/construction-material-save";
		}

		if(constructionMaterial.getUuid() == null) constructionMaterial.setUuid(UUID.randomUUID());
		constructionMaterialSaveService.execute(constructionMaterial);
		return "redirect:/construction-materials/list/actives";
	}

	@GetMapping("/delete/{uuid}")
	public String delete(@PathVariable String uuid, Model model) {
		var constructionMaterialDTO = constructionMaterialByIdService.execute(uuid);
		constructionMaterialDeletionService.execute(constructionMaterialDTO);

		return "redirect:/construction-materials/list/actives";
	}	
}

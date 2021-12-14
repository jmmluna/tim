package es.jmmluna.tim.application.service.construction_material;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.application.service.EElementList;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterial;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialListingService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialListingService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public List<ConstructionMaterialDTO> execute(EElementList type) {
		List<ConstructionMaterialDTO> customers = new ArrayList<ConstructionMaterialDTO>();

		if (type == EElementList.ALL)
			customers = this.toConstructionMaterialDTOList(this.repository.getAll());
		else if (type == EElementList.ACTIVE)
			customers = this.toConstructionMaterialDTOList(this.repository.getActives());
		else
			customers = this.toConstructionMaterialDTOList(this.repository.getInactives());

		return customers;
	}

	private List<ConstructionMaterialDTO> toConstructionMaterialDTOList(List<ConstructionMaterial> constructionMaterials) {
		var dtos = new ArrayList<ConstructionMaterialDTO>();

//		for (var customer : constructionMaterials) {
//
//			dtos.add(ConstructionMaterialService.toDTO(customer));
//		}
		
		constructionMaterials.forEach(constructionMaterial -> dtos.add(ConstructionMaterialService.toDTO(constructionMaterial)));
		return dtos;
	}

}

package es.jmmluna.tim.application.service.construction_material;

import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterial;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialId;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;
import es.jmmluna.tim.domain.model.customer.Customer;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.customer.Dni;

public abstract class ConstructionMaterialService {
	
	protected ConstructionMaterialRepository repository;

	public ConstructionMaterialService(ConstructionMaterialRepository repository) {
		this.repository = repository;
	}

	public static ConstructionMaterialDTO toDTO(ConstructionMaterial constructionMaterial) {

		ConstructionMaterialDTO constructionMaterialDTO = new ConstructionMaterialDTO();
		constructionMaterialDTO.setUuid(constructionMaterial.getConstructionMaterialId().getValue());
		constructionMaterialDTO.setDescription(constructionMaterial.getDescription());
		constructionMaterialDTO.setPrice(constructionMaterial.getPrice());		
		constructionMaterialDTO.setExpirationDate(constructionMaterial.getExpirationDate());

		return constructionMaterialDTO;
	}

	public static ConstructionMaterial toModel(ConstructionMaterialDTO constructionMaterialDTO) {

		ConstructionMaterial constructionMaterial = new ConstructionMaterial(ConstructionMaterialId.of(constructionMaterialDTO.getUuid()));		
		constructionMaterial.setDescription(constructionMaterialDTO.getDescription());
		constructionMaterial.setPrice(constructionMaterialDTO.getPrice());		
		constructionMaterial.setExpirationDate(constructionMaterialDTO.getExpirationDate());

		return constructionMaterial;
	}

}

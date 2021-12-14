package es.jmmluna.tim.application.service.construction_material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialSaveService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialSaveService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public ConstructionMaterialDTO execute(ConstructionMaterialDTO constructionMaterialDTO) {
		var constructionMaterial = this.repository.save(ConstructionMaterialService.toModel(constructionMaterialDTO));
		return ConstructionMaterialService.toDTO(constructionMaterial);
	}

}

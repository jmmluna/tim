package es.jmmluna.tim.application.service.construction_material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialDeletionService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialDeletionService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public ConstructionMaterialDTO execute(ConstructionMaterialDTO customerDTO) {
		var customer = this.repository.delete(ConstructionMaterialService.toModel(customerDTO));
		return ConstructionMaterialService.toDTO(customer);
	}

}

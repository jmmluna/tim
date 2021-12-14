package es.jmmluna.tim.application.service.construction_material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialDeletionByIdService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialDeletionByIdService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public ConstructionMaterialDTO execute(String uuid) {
		var customer = this.repository.delete(uuid);
		return ConstructionMaterialService.toDTO(customer);
	}

}

package es.jmmluna.tim.application.service.construction_material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialId;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialByIdService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialByIdService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public ConstructionMaterialDTO execute(String uuid) {
		var customer = this.repository.getById(ConstructionMaterialId.of(uuid));
		return ConstructionMaterialService.toDTO(customer);
	}

}
	
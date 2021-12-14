package es.jmmluna.tim.application.service.construction_material;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Service
public class ConstructionMaterialCountService extends ConstructionMaterialService {

	@Autowired
	public ConstructionMaterialCountService(ConstructionMaterialRepository repository) {
		super(repository);
	}

	public Long execute() {
		return this.repository.getActiveCount();		
	}	
}

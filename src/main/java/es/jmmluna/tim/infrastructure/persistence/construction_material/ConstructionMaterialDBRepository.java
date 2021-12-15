package es.jmmluna.tim.infrastructure.persistence.construction_material;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterial;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialId;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialRepository;

@Component
public class ConstructionMaterialDBRepository implements ConstructionMaterialRepository {

	@Autowired
	private ConstructionMaterialJpaRepository constructionMaterialRepository;

	@Override
	public long getActiveCount() {
		return constructionMaterialRepository.countByExpirationDate(null);
	}

	@Override
	public ConstructionMaterial save(ConstructionMaterial constructionMaterial) {		
		var savedEntity = constructionMaterialRepository.save(ConstructionMaterialJpaEntity.of(constructionMaterial));
		return savedEntity.toModel();
	}

	@Override
	public ConstructionMaterial getById(ConstructionMaterialId constructionMaterialId) {
		Optional<ConstructionMaterialJpaEntity> result = constructionMaterialRepository.findById(constructionMaterialId.getValue());
		var entity = result.get();
		return entity.toModel();
	}

//	@Override
//	public ConstructionMaterial getByName(String name) {
//		var entity = constructionMaterialRepository.findByName(name);
//		return entity.toModel();
//	}

	@Override
	public ConstructionMaterial delete(ConstructionMaterial constructionMaterial) {
		// TODO: no modificar directamente el parámetro de entrada
		constructionMaterial.setExpirationDate(new Date());
		return this.save(constructionMaterial);
	}

	@Override
	public ConstructionMaterial delete(String uuid) {
		var constructionMaterial = this.getById(ConstructionMaterialId.of(uuid));
		return this.delete(constructionMaterial);
	}

	@Override
	public List<ConstructionMaterial> getAll() {
		var entities = constructionMaterialRepository.findAll();
		return toEntityList(entities);
	}

	@Override
	public List<ConstructionMaterial> getActives() {
		var entities = constructionMaterialRepository.findByExpirationDate(null);
		return toEntityList(entities);

	}

	@Override
	public List<ConstructionMaterial> getInactives() {
		var entities = constructionMaterialRepository.findByExpirationDateIsNotNull();
		return toEntityList(entities);
	}

	private List<ConstructionMaterial> toEntityList(List<ConstructionMaterialJpaEntity> entities) {
		var constructionMaterials = new ArrayList<ConstructionMaterial>();
		entities.forEach(entity -> constructionMaterials.add(entity.toModel()));
		return constructionMaterials;
	}

}

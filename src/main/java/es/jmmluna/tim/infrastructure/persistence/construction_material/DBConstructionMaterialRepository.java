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
public class DBConstructionMaterialRepository implements ConstructionMaterialRepository {

	@Autowired
	private JpaConstructionMaterialRepository constructionMaterialepository;

	@Override
	public long getActiveCount() {
		return constructionMaterialepository.countByExpirationDate(null);
	}

	@Override
	public ConstructionMaterial save(ConstructionMaterial constructionMaterial) {		
		var savedEntity = constructionMaterialepository.save(JpaConstructionMaterialEntity.of(constructionMaterial));
		return savedEntity.toModel();
	}

	@Override
	public ConstructionMaterial getById(ConstructionMaterialId constructionMaterialId) {
		Optional<JpaConstructionMaterialEntity> result = constructionMaterialepository.findById(constructionMaterialId.getValue());
		var entity = result.get();
		return entity.toModel();
	}

	@Override
	public ConstructionMaterial getByName(String name) {
		var entity = constructionMaterialepository.findByName(name);
		return entity.toModel();
	}

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
		var entities = constructionMaterialepository.findAll();
		return toEntityList(entities);
	}

	@Override
	public List<ConstructionMaterial> getActives() {
		var entities = constructionMaterialepository.findByExpirationDate(null);
		return toEntityList(entities);

	}

	@Override
	public List<ConstructionMaterial> getInactives() {
		var entities = constructionMaterialepository.findByExpirationDateIsNotNull();
		return toEntityList(entities);
	}

	private List<ConstructionMaterial> toEntityList(List<JpaConstructionMaterialEntity> entities) {
		var constructionMaterials = new ArrayList<ConstructionMaterial>();
		entities.forEach(entity -> constructionMaterials.add(entity.toModel()));
		return constructionMaterials;
	}

}

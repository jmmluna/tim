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
		var jpaCustomerEntity = result.get();
		return jpaCustomerEntity.toModel();
	}

	@Override
	public ConstructionMaterial getByName(String name) {
		var jpaEmployeeEntity = constructionMaterialepository.findByName(name);
		return jpaEmployeeEntity.toModel();
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

//	@Override
//	public ConstructionMaterial delete(UUID uuid) {
//		return this.delete(uuid.toString());
//	}

	@Override
	public List<ConstructionMaterial> getAll() {
		var jpaCustomerEntities = constructionMaterialepository.findAll();
		return toCustomerList(jpaCustomerEntities);
	}

	@Override
	public List<ConstructionMaterial> getActives() {
		var jpaCustomerEntities = constructionMaterialepository.findByExpirationDate(null);
		return toCustomerList(jpaCustomerEntities);

	}

	@Override
	public List<ConstructionMaterial> getInactives() {
		var jpaCustomerEntities = constructionMaterialepository.findByExpirationDateIsNotNull();
		return toCustomerList(jpaCustomerEntities);
	}

	private List<ConstructionMaterial> toCustomerList(List<JpaConstructionMaterialEntity> jpaCustomerEntities) {
		var constructionMaterials = new ArrayList<ConstructionMaterial>();
		jpaCustomerEntities.forEach(jpaCustomerEntity -> constructionMaterials.add(jpaCustomerEntity.toModel()));
		return constructionMaterials;
	}

}

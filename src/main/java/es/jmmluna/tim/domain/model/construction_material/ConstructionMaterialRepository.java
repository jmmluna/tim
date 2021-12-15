package es.jmmluna.tim.domain.model.construction_material;

import java.util.List;

public interface ConstructionMaterialRepository { //extends GenericRepository<ConstructionMaterial, ConstructionMaterialId> {
	public ConstructionMaterial save(ConstructionMaterial model);

	public List<ConstructionMaterial> getAll();

	public ConstructionMaterial getById(ConstructionMaterialId id);

	public long getActiveCount();

	public ConstructionMaterial delete(ConstructionMaterial model);

	public ConstructionMaterial delete(String id);

	public List<ConstructionMaterial> getActives();

	public List<ConstructionMaterial> getInactives();
}

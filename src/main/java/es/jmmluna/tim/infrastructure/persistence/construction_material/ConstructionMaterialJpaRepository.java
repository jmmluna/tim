package es.jmmluna.tim.infrastructure.persistence.construction_material;

import java.util.UUID;

import org.springframework.stereotype.Repository;

import es.jmmluna.tim.infrastructure.persistence.JpaGenericRepository;

@Repository
public interface ConstructionMaterialJpaRepository  extends JpaGenericRepository<ConstructionMaterialJpaEntity, UUID> { //extends JpaRepository<JpaConstructionMaterialEntity, UUID> {  

//	public List<JpaConstructionMaterialEntity> findByExpirationDate(Date expirationDate);
//
//	public List<JpaConstructionMaterialEntity> findByExpirationDateIsNotNull();
	
//	public JpaConstructionMaterialEntity findByName(String name);
	
//	public long countByExpirationDate(Date expirationDate);
}
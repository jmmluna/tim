package es.jmmluna.tim.infrastructure.persistence.construction_material;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterial;
import es.jmmluna.tim.domain.model.construction_material.ConstructionMaterialId;
import lombok.Data;

@Entity()
@Table(name = "TIM_CONSTRUCTION_MATERIALS", schema = "TIM")
@Data
public class ConstructionMaterialJpaEntity {
	@Id
	private UUID uuid;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "PRICE")
	private Double price;

	@Column(name = "EXPIRATION_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;

	public ConstructionMaterial toModel() {
		ConstructionMaterial constructionMaterial = new ConstructionMaterial(
				ConstructionMaterialId.of(this.uuid.toString()));
		constructionMaterial.setDescription(this.description);
		constructionMaterial.setPrice(this.price);
		constructionMaterial.setExpirationDate(this.expirationDate);
		return constructionMaterial;
	}

	public static ConstructionMaterialJpaEntity of(ConstructionMaterial constructionMaterial) {
		ConstructionMaterialJpaEntity entity = new ConstructionMaterialJpaEntity();
		entity.setUuid(constructionMaterial.getConstructionMaterialId().getValue());
		entity.setDescription(constructionMaterial.getDescription());
		entity.setPrice(constructionMaterial.getPrice());
		entity.setExpirationDate(constructionMaterial.getExpirationDate());
		return entity;
	}

}

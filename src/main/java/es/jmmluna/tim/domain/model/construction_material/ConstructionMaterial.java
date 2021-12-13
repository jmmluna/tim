package es.jmmluna.tim.domain.model.construction_material;

import java.util.Date;

public class ConstructionMaterial {
	private ConstructionMaterialId constructionMaterialId;
	private String description;
	private Double price;
	private Date expirationDate;

	public ConstructionMaterial(ConstructionMaterialId constructionMaterialId) {
		this.constructionMaterialId = constructionMaterialId;
	}

	public ConstructionMaterialId getConstructionMaterialId() {
		return constructionMaterialId;
	}

	public void setConstructionMaterialId(ConstructionMaterialId constructionMaterialId) {
		this.constructionMaterialId = constructionMaterialId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

}

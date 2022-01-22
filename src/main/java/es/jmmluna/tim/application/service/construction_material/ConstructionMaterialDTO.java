package es.jmmluna.tim.application.service.construction_material;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.application.service.Util;

public class ConstructionMaterialDTO {
	private UUID uuid;
	private String description;
	private Double price;

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expirationDate;

	public ConstructionMaterialDTO() {
		
	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
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
		this.price = Util.to2Decimal(price);
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}

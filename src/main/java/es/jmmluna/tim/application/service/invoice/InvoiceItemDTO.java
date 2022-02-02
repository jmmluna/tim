package es.jmmluna.tim.application.service.invoice;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.Util;
import lombok.Data;

import java.util.UUID;

@Data
public class InvoiceItemDTO extends DTO {
	private UUID invoicetId;
	private String description;
	private Integer quantity;
	private Double price;

	public InvoiceItemDTO() {
	}

	public InvoiceItemDTO(UUID uuid, String description, Integer quantity, Double price) {
		this.uuid = uuid;
		this.description = description;
		this.quantity = quantity;
		setPrice(price);
	}
	
	public void setPrice(Double price) {
		this.price = Util.to2Decimal(price);
	}
	
	public Double getCost() {
		return  Util.to2Decimal(price * quantity);
	}
}

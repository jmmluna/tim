package es.jmmluna.tim.application.service.work;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.Util;
import lombok.Data;

@Data
public class WorkItemDTO extends DTO {
	private UUID workId;
	private String description;
	private Integer quantity;
	private Double price;
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;

	public WorkItemDTO() {

	}

	public WorkItemDTO(UUID uuid, String description, Integer quantity, Double price, Date date) {
		this.uuid = uuid;
		this.description = description;
		this.quantity = quantity;
		setPrice(price);
		this.date = date;
	}
	
	public void setPrice(Double price) {
		this.price = Util.to2Decimal(price);
	}
	
	public Double getCost() {
		return  Util.to2Decimal(price * quantity);
	}
}

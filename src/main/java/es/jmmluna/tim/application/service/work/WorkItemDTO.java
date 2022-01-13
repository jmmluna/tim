package es.jmmluna.tim.application.service.work;

import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class WorkItemDTO extends DTO {
//	private UUID workId;
	private String description;
	private Integer quantity;
	private Double price;

	public WorkItemDTO() {

	}

	public WorkItemDTO(UUID uuid, String description, Integer quantity, Double price) {
		this.uuid = uuid;
		this.description = description;
		this.quantity = quantity;
		this.price = price;
	}
}

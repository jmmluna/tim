package es.jmmluna.tim.application.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public abstract class DTO {

	protected UUID uuid;	

	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expirationDate;
}

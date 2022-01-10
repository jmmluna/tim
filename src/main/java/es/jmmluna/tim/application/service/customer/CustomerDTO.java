package es.jmmluna.tim.application.service.customer;

import java.util.Date;
import java.util.UUID;

import es.jmmluna.tim.application.service.DTO;
import lombok.Data;

@Data
public class CustomerDTO extends DTO {
	private String name;
	private String surnames;
	private String dni;
	private String address;
	private Integer postalCode;
	private String town;
	private String phone;
	private String email;

	public CustomerDTO() {

	}

	public CustomerDTO(UUID uuid, String dni, String name, String surnames, String address, Integer postalCode,
			String town, String phone, String email) {
		this.uuid = uuid;
		this.dni = dni;
		this.name = name;
		this.surnames = surnames;
		this.address = address;
		this.postalCode = postalCode;
		this.town = town;
		this.phone = phone;
		this.email = email;
	}

	public CustomerDTO(UUID uuid, String dni, String name, String surnames, String address, Integer postalCode,
			String town, String phone, String email, Date expirationDate) {
		this(uuid, dni, name, surnames, address, postalCode, town, phone, email);
		this.setExpirationDate(expirationDate);
	}
}

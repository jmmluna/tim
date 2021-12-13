package es.jmmluna.tim.application.service.customer;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public class CustomerDTO {
	private UUID uuid;

	private String dni;
	private String name;
	private String surnames;
	private String address;
	private String phone;
	private String email;
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private Date expirationDate;

	public CustomerDTO() {
		// this.uuid = UUID.randomUUID();
	}

//	public CustomerDTO(UUID uuid) {
//		this.uuid = uuid;
//	}

	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public UUID getUuid() {
		return uuid;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getDni() {
		return dni;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurnames() {
		return surnames;
	}

	public void setSurnames(String surnames) {
		this.surnames = surnames;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
}

package es.jmmluna.tim.infrastructure.persistence.customer;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity()
@Table(name = "TIM_CUSTOMERS", schema = "TIM")
@Data
public class CustomerEntity {
	@Id
	private UUID uuid;
	@Column(name="DNI")
	private String dni;
	@Column(name = "NAME")
	private String name;
	@Column(name = "SURNAMES")
	private String surnames;
	@Column(name = "PHONE")
	private String phone;
	@Column(name = "ADDRESS")
	private String address;
	@Column(name = "POSTAL_CODE")
	private Integer postalCode;
	@Column(name = "TOWN")
	private String town;
	@Column(name = "EMAIL")
	private String email;
	@Column(name="EXPIRATION_DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;	
}

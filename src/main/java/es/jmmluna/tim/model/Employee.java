package es.jmmluna.tim.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity()
@Table(name = "TIM_EMPLOYEES", schema = "TIM")
@Data

public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "NAME")
	private String name;

	@Column(name = "SURNAMES")
	private String surnames;

	@Column(name="CUSTOMER_HOUR_PRICE")
	private Double customerHourPrice;

	@Column(name="EMPLOYEE_HOUR_PRICE")
	private Double employeeHourPrice;
	
	
	@Column(name="EXPIRATION_DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
}
package es.jmmluna.tim.infrastructure.persistence.employee;

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
@Table(name = "TIM_WORKS_EMPLOYEES", schema = "TIM")
@Data
public class EmployeeHourJpaEntity {

	@Id
	private UUID uuid;

	@Column(name = "EMPLOYEES_ID")
	private Long employeeId;
	
	@Column(name = "WORK_UUID")
	private UUID workId;

	@Column(name = "HOURS")
	private Double hours;

	@Column(name="PRICE")
	private Double price;
		
	@Column(name="DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date date;
}
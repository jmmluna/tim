package es.jmmluna.tim.infrastructure.persistence.employee;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.domain.model.employee.Employee;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import lombok.Data;

@Entity()
@Table(name = "TIM_EMPLOYEES", schema = "TIM")
@Data
public class JpaEmployeeEntity {

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
//	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
	
	public Employee toEmployee()  {
		var employee = new Employee(EmployeeId.of(this.id));
		employee.setName(this.name);
		employee.setSurnames(this.surnames);
		employee.setCustomerHourPrice(this.customerHourPrice);
		employee.setEmployeeHourPrice(this.employeeHourPrice);
		employee.setExpirationDate(this.expirationDate);
		return employee;
	}
	
	public static JpaEmployeeEntity toEntity(Employee employee) {
		var entity = new JpaEmployeeEntity();
		entity.setId(employee.getEmployeeId().getValue());		
		entity.setName(employee.getName());
		entity.setSurnames(employee.getSurnames());		
		entity.setCustomerHourPrice(employee.getCustomerHourPrice());
		entity.setEmployeeHourPrice(employee.getEmployeeHourPrice());
		entity.setExpirationDate(employee.getExpirationDate());
		return entity;
	}
}
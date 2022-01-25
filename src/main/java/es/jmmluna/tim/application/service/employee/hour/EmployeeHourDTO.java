package es.jmmluna.tim.application.service.employee.hour;

import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.employee.EmployeeDTO;
import es.jmmluna.tim.application.service.work.WorkDTO;
import lombok.Data;

@Data
public class EmployeeHourDTO extends DTO {

	private EmployeeDTO employeeDTO;
	private WorkDTO workDTO;
	private Double price;
	private Double hours;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	

	public EmployeeHourDTO() {
		this.employeeDTO = new EmployeeDTO();
		this.workDTO = new WorkDTO();
	}
	
	public EmployeeHourDTO(UUID uuid, EmployeeDTO employeeDTO,  WorkDTO workDTO, Double price, Double hours, Date date) {
		this.uuid = uuid;
		this.employeeDTO = employeeDTO;
		this.workDTO = workDTO;
		this.price = price;
		this.hours = hours;
		this.date = date;
	}
	
}

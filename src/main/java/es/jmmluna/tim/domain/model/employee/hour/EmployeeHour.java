package es.jmmluna.tim.domain.model.employee.hour;

import java.util.Date;

import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.employee.EmployeeId;
import es.jmmluna.tim.domain.model.work.WorkId;

public class EmployeeHour {
	private EmployeeHourId employeeHourId;
	private EmployeeId employeeId;
	private WorkId workId;
	private Date date;
	private Double hours;
	private Price price;

	public EmployeeHour(EmployeeHourId employeeHourId, EmployeeId employeeId, WorkId workId, Date date, Double hours, Price price) {
		this.employeeHourId = employeeHourId;
		this.employeeId = employeeId;
		this.workId = workId;
		this.date = date;
		this.hours = hours;
		this.price = price;
	}

	public EmployeeHourId getEmployeeHourId() {
		return employeeHourId;
	}

	public EmployeeId getEmployeeId() {
		return employeeId;
	}

	public WorkId getWorkId() {
		return workId;
	}

	public Date getDate() {
		return date;
	}

	public Double getHours() {
		return hours;
	}

	public Price getPrice() {
		return price;
	}
}

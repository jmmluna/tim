package es.jmmluna.tim.domain.model.employee.hour;

import java.time.Month;
import java.time.format.TextStyle;
import java.util.Locale;

public interface EmployeeHourSummary {
	
	public String getName();
	public String getSurnames();
	public Integer getMonth();	
	public Integer getYear();
	public Double getSalary();
	public Double getTotalHours();
	public Integer getEntries();
	public default String getMonthName() {
		var month = Month.of(getMonth());
		return month.getDisplayName(TextStyle.FULL, new Locale("es", "ES")).toUpperCase();
	}
	
}

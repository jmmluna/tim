package es.jmmluna.tim.domain.model.employee.hour;

public interface EmployeeHourSummary {
	
	public String getName();
	public String getSurnames();
	public Integer getMonth();
	public Integer getYear();
	public Double getTotalPrice();
	public Double getTotalHours();
	public Integer getEntries();
	
	
	//select e.name, e.surnames, MONTH(we.date) as month, YEAR(we.date) as year, 
	//SUM(we.price* we.hours) as total_price, SUM(we.hours) as total_hours, count(*) as entries
	
//	public String getName() {
//		return name;
//	}
//	public String getSurnames() {
//		return surnames;
//	}
//	public Integer getMonth() {
//		return month;
//	}
//	public Integer getYear() {
//		return year;
//	}
//	public Double getTotalPrice() {
//		return totalPrice;
//	}
//	public Double getTotalHours() {
//		return totalHours;
//	}
//	public Integer getEntries() {
//		return entries;
//	}
//	private String name;
//	private String surnames;
//	private Integer month;
//	private Integer year;
//	private Double totalPrice;
//	private Double totalHours;
//	private Integer entries;
//	public EmployeeHourSummary(String name, String surnames, Integer month, Integer year, Double totalPrice,
//			Double totalHours, Integer entries) {
//		
//		this.name = name;
//		this.surnames = surnames;
//		this.month = month;
//		this.year = year;
//		this.totalPrice = totalPrice;
//		this.totalHours = totalHours;
//		this.entries = entries;
//	}
	
	
}

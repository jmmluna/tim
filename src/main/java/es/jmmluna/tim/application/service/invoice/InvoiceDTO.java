package es.jmmluna.tim.application.service.invoice;

import es.jmmluna.tim.application.service.DTO;
import es.jmmluna.tim.application.service.Util;
import es.jmmluna.tim.application.service.budget.BudgetItemDTO;
import es.jmmluna.tim.application.service.customer.CustomerDTO;
import es.jmmluna.tim.application.service.work.WorkDTO;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
public class InvoiceDTO extends DTO {
	private CustomerDTO customerDTO;
	private WorkDTO workDTO;
	private String description;
	private Integer invoiceNumber;
	private Integer year;
//	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	private Double cost = 0.0;
	private List<InvoiceItemDTO> invoiceItems;
	private Double discountRate;
	private Double ivaRate;
	private Double reRate;
	private Double irpfRate;

	public InvoiceDTO() {
		this.invoiceItems = new ArrayList<InvoiceItemDTO>();
	}

	public InvoiceDTO(UUID uuid, WorkDTO workDTO, CustomerDTO customerDTO, String description, Integer invoiceNumber,
			Integer year, Date date, Double cost, List<InvoiceItemDTO> invoiceItems, Double discountRate,
			Double ivaRate, Double reRate, Double irpfRate) {
		this.uuid = uuid;
		this.workDTO = workDTO;
		this.customerDTO = customerDTO;
		this.description = description;
		this.invoiceNumber = invoiceNumber;
		this.year = year;
		this.date = date;
		this.setCost(cost);
		this.invoiceItems = new ArrayList<>(invoiceItems);
		this.discountRate = discountRate==null?0.0:discountRate;
		this.ivaRate = ivaRate==null?0.0:ivaRate;
		this.reRate = reRate==null?0.0:reRate;
		this.irpfRate = irpfRate==null?0.0:irpfRate;
	}

	public InvoiceDTO(UUID uuid, WorkDTO workDTO, CustomerDTO customerDTO, String description, Integer invoiceNumber,
			Integer year, Date date, Double cost, List<InvoiceItemDTO> invoiceItems, Double discountRate,
			Double ivaRate, Double reRate, Double irpfRate, Date expirationDate) {
		this(uuid, workDTO, customerDTO, description, invoiceNumber, year, date, cost, invoiceItems, discountRate,
				ivaRate, reRate, irpfRate);
		this.setExpirationDate(expirationDate);
	}

	public void add(InvoiceItemDTO invoiceItemDTO) {
		invoiceItems.add(invoiceItemDTO);
	}

	public void remove(Integer index) {
		invoiceItems.remove(index.intValue());
	}

	public void setCost(Double cost) {
		this.cost = Util.to2Decimal(cost);
	}

	public Double getSubtotal() {
		return this.cost;
	}

	public Double getDiscount() {
		return Util.to2Decimal(this.cost * (this.discountRate / 100));
	}

	// Base imponible: Sin impuestos ni retenciones
	public Double getTaxBase() {
		return Util.to2Decimal(getSubtotal() - getDiscount());
	}

	public Double getIvaTotal() {
		return Util.to2Decimal(getTaxBase() * (this.ivaRate / 100));
	}

	public Double getReTotal() {
		return Util.to2Decimal(getTaxBase() * (this.reRate / 100));
	}

	public Double getIrpfTotal() {
		return Util.to2Decimal(getTaxBase() * (this.irpfRate / 100));
	}

	public Double getTotal() {
		var total = getTaxBase() + getIvaTotal() + getIrpfTotal() + getReTotal();
		return Util.to2Decimal(total);
	}
	
	public Boolean isTaxBase() {
		return !((ivaRate == 0.0) && (irpfRate == 0.0) && (reRate == 0.0)); 
	}
}

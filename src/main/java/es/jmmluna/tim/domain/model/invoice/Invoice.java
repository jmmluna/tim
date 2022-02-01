package es.jmmluna.tim.domain.model.invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import es.jmmluna.tim.domain.model.NotFoundItemException;
import es.jmmluna.tim.domain.model.Price;
import es.jmmluna.tim.domain.model.customer.CustomerId;
import es.jmmluna.tim.domain.model.work.WorkId;

public class Invoice {
	private InvoiceId invoiceId;
	private WorkId workId;
	private CustomerId customerId;
	private Integer invoiceNumber;
	private String description;
	private Date date;
	private Integer year;	
	private final List<InvoiceItem> invoiceItems;
	private Date expirationDate;
	private Price totalCost;
	private Double discountRate;
	private Double ivaRate;
	private Double reRate;
	private Double irpfRate;

	public Invoice(InvoiceId invoiceId, WorkId workId, CustomerId customerId, Integer invoiceNumber, String description,
			Date date, Integer year, List<InvoiceItem> invoiceItems, Double discountRate, Double ivaRate, Double reRate,
			Double irpfRate) {
		this.invoiceId = invoiceId;
		this.workId = workId;
		this.customerId = customerId;
		this.invoiceNumber = invoiceNumber;
		this.description = description;
		this.date = date;
		this.year = year;
		this.invoiceItems = new ArrayList<>(invoiceItems);
		this.discountRate = discountRate;
		this.ivaRate = ivaRate;
		this.reRate = reRate;
		this.irpfRate = irpfRate;

		totalCost = calculateTotalCost();
	}

	public Invoice(InvoiceId invoiceId, WorkId workId, CustomerId customerId, Integer invoiceNumber, String description,
			Date date, Integer year, List<InvoiceItem> invoiceItems, Date expirationDate, Double discountRate, Double ivaRate,
			Double reRate, Double irpfRate) {

		this(invoiceId, workId, customerId, invoiceNumber, description, date, year, invoiceItems, discountRate, ivaRate,
				reRate, irpfRate);

		this.expirationDate = expirationDate;

	}

	public WorkId getWorkId() {
		return workId;
	}

	public CustomerId getCustomerId() {
		return customerId;
	}

	public String getDescription() {
		return description;
	}

	public Date getDate() {
		return date;
	}
	
	public Integer getYear() {
		return year;
	}


	public Date getExpirationDate() {
		return expirationDate;
	}

	public List<InvoiceItem> getInvoiceItems() {
		return Collections.unmodifiableList(invoiceItems);
	}

	public Price getTotalCost() {
		return totalCost;
	}

	public InvoiceId getInvoiceId() {
		return invoiceId;
	}

	public Integer getInvoiceNumber() {
		return invoiceNumber;
	}

	public Double getDiscountRate() {
		return discountRate;
	}

	public Double getIvaRate() {
		return ivaRate;
	}

	public Double getReRate() {
		return reRate;
	}

	public Double getIrpfRate() {
		return irpfRate;
	}

	public void add(InvoiceItem invoiceItem) {
		invoiceItems.add(invoiceItem);
		totalCost = totalCost.plus(invoiceItem.getCost());
	}

	public void remove(InvoiceItem invoiceItem) {
		if (invoiceItems.remove(invoiceItem))
			totalCost = totalCost.minus(invoiceItem.getCost());
		else
			throw new NotFoundItemException("No se ha encontrado el elemento en la factura");
	}

	private Price calculateTotalCost() {
		if (invoiceItems.isEmpty())
			return Price.of(0.0);
		return invoiceItems.stream().map(InvoiceItem::getCost).reduce(Price::plus).get();
	}
}

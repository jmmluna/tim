package es.jmmluna.tim.domain.model.invoice;

import java.util.Date;
import java.util.Objects;

import es.jmmluna.tim.domain.model.Price;

public class InvoiceItem {	
	private InvoiceItemId invoiceItemId;
	private String description;
	private Integer quantity;
	private Price price;
	private Date date;

	public InvoiceItem(InvoiceItemId invoiceItemId, String description, Integer quantity, Price price) {
		this.invoiceItemId = invoiceItemId;
		this.description = description;
		this.quantity = quantity;
		this.price = price;		
	}

	public InvoiceItemId getInvoiceItemId() {
		return invoiceItemId;
	}

	public String getDescription() {
		return description;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public Price getPrice() {
		return price;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Price getCost() {
        return getPrice()
            .multipliedBy(quantity);
    }
	
	@Override
	public int hashCode() {
		return Objects.hash(invoiceItemId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InvoiceItem other = (InvoiceItem) obj;
		return Objects.equals(invoiceItemId, other.invoiceItemId);
	}


}

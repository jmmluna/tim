package es.jmmluna.tim.infrastructure.persistence.invoice;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity()
@Table(name = "TIM_INVOICES", schema = "TIM")
@Data
public class InvoiceEntity {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;
	@Column(name = "CUSTOMER_UUID")
	private UUID customerId;
	@Column(name = "WORK_UUID")
	private UUID workId;
	@Column(name = "INVOICE_NUMBER")
	private Integer invoiceNumber;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "YEAR")
	private Integer year;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "invoiceEntity") // fetch = FetchType.EAGER
	private List<InvoiceItemEntity> invoiceItems;

	@Column(name = "DISCOUNT_RATE")
	private Double discountRate;
	
	@Column(name = "IVA_RATE")
	private Double ivaRate;
	
	@Column(name = "RE_RATE")
	private Double reRate;
	
	@Column(name = "IRPF_RATE")
	private Double irpfRate;
	
	
	@Column(name = "EXPIRATION_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
}

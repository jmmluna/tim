package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import es.jmmluna.tim.infrastructure.persistence.budget.BudgetEntity;
import es.jmmluna.tim.infrastructure.persistence.customer.CustomerEntity;
import lombok.Data;

@Entity()
@Table(name = "TIM_WORKS", schema = "TIM")
@Data
public class WorkEntity {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;

	@OneToOne
	@JoinColumn(name = "CUSTOMERS_UUID", referencedColumnName = "uuid")
	private CustomerEntity customer;
	
	@OneToOne
	@JoinColumn(name = "BUDGETS_UUID", referencedColumnName = "uuid")
//	@Transient
	private BudgetEntity budget;
	
	@Column(name = "STATUS")
	private Integer status;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "YEAR")
	private Integer year;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workEntity")
	private Set<WorkItemEntity> workItems;

	@Column(name = "EXPIRATION_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
}

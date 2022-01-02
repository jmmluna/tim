package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.Date;
import java.util.List;
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

import es.jmmluna.tim.infrastructure.persistence.customer.CustomerEntity;
import lombok.Data;

@Entity()
@Table(name = "TIM_BUDGETS", schema = "TIM")
@Data
public class BudgetEntity {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "CUSTOMERS_UUID", referencedColumnName = "uuid")
	private CustomerEntity customer;
	@Column(name = "BUDGET_NUMBER")
	private Integer budgetNumber;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "YEAR")
	private Integer year;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "budgetEntity") // fetch = FetchType.EAGER
	private List<BudgetItemEntity> budgetItems;

	@Column(name = "EXPIRATION_DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@Temporal(TemporalType.DATE)
	private Date expirationDate;
}

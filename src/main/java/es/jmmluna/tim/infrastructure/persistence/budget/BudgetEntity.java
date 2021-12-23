package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity()
@Table(name = "TIM_BUDGETS", schema = "TIM")
@Data
public class BudgetEntity {
	@Id
	private UUID uuid;
	@Column(name="BUDGET_NUMBER")
	private Long budgetNumber;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "DATE")
	private Date date;
	@Column(name = "YEAR")
	private Integer year;

	@OneToMany(mappedBy="buddgetId")
	private List<BudgetItemEntity> budgetItems;
	
	@Column(name="EXPIRATION_DATE")
	@DateTimeFormat(pattern="dd/MM/yyyy")	
	@Temporal(TemporalType.DATE)
	private Date expirationDate;	
}

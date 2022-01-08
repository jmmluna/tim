package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;

@Entity()
@Table(name = "TIM_BUDGET_ITEMS", schema = "TIM")
@Data
public class BudgetItemEntity {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;
	@ManyToOne(optional = false,  fetch = FetchType.EAGER) //cascade = CascadeType.ALL,
	@JoinColumn(name = "BUDGET_UUID", nullable = false)
	private BudgetEntity budgetEntity;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "AMOUNT")
	private Integer amount;
	@Column(name = "PRICE")
	private Double price;
}

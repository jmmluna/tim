package es.jmmluna.tim.infrastructure.persistence.budget;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	private UUID uuid;
	@ManyToOne
    @JoinColumn(name="uuid", nullable=false)
	@Column(name="BUDGET_UUID")
	private UUID budgetId;
	@Column(name="DESCRIPTION")
	private String description;
	@Column(name="AMOUNT")
	private Integer amount;
	@Column(name="PRICE")
	private Double price;

}

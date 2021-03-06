package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Entity()
@Table(name = "TIM_WORK_ITEMS", schema = "TIM")
@Data
public class WorkItemEntity {
	@Id
	@Column(columnDefinition = "BINARY(16)")
	private UUID uuid;
	@ManyToOne(optional = false,  fetch = FetchType.EAGER)
	@JoinColumn(name = "WORK_UUID", nullable = false)
	private WorkEntity workEntity;
	@Column(name = "DESCRIPTION")
	private String description;
	@Column(name = "AMOUNT")
	private Integer amount;
	@Column(name = "PRICE")
	private Double price;
	@Column(name = "DATE")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
	private Date date;
}

package es.jmmluna.tim.infrastructure.persistence.work;

import java.util.*;

import javax.persistence.*;

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

    //	@OneToOne
//	@JoinColumn(name = "BUDGETS_UUID", referencedColumnName = "uuid")
//	@Transient
//	private BudgetEntity budget;
    @Column(name = "BUDGETS_UUID")
    private UUID budgetId;

    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "DESCRIPTION")
    private String description;
    @Column(name = "DATE")
    private Date date;


    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "workEntity")
//	@Transient
    private List<WorkItemEntity> workItems;// = new HashSet<>();

    @Column(name = "EXPIRATION_DATE")
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @Temporal(TemporalType.DATE)
    private Date expirationDate;
}

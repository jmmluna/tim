package es.jmmluna.tim.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Entity( )
@Table(name="TIM_EMPLOYEES",schema="TIM")
@Data

public class Employee {
    
	@Id    	
//	@Column(name="ID")
    private Long id;
    
    @Column(name="NAME")
    private String name;
    
    @Column(name="SURNAMES")
    private String surnames;

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
    
//	public String getSurnames() {
//		return surnames;
//	}

//	public void setSurnames(String surnames) {
//		this.surnames = surnames;
//	}
    
}
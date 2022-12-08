package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfClassBatches"}, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfClassBatches"}, callSuper = false)
@Entity
@Table(name = "BUDGET")
public class Budget extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Budget(String budgetName) {
		super();
		this.budgetName = budgetName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGET_ID", columnDefinition = "INT")
	private Integer id;
	
//	@OneToMany(mappedBy = "budget")
//	private Set<ClassBatch> setOfClassBatches;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "BUDGET_NAME", columnDefinition = "NVARCHAR(255)")
	private String budgetName;

}

package com.example.demo.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "classBatch"}, callSuper = false)
@EqualsAndHashCode(exclude = { "classBatch"}, callSuper = false)
@Entity
@Table(name = "BUDGET_DETAIL")
public class BudgetDetail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BUDGET_DETAIL_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "ITEM", columnDefinition = "NVARCHAR(255)")
	private String item;
	
	@Column(name = "UNIT", columnDefinition = "NVARCHAR(255)")
	private String unit;
	
	@Column(name = "UNIT_EXPENSE", columnDefinition = "FLOAT")
	private Double unitExpense;
	
	@Column(name = "QUANTITY", columnDefinition = "INT")
	private Integer quantity;
	
	@Column(name = "AMOUNT", columnDefinition = "FLOAT")
	private Double amount;
	
	@Column(name = "TAX", columnDefinition = "INT")
	private Integer tax;
	
	@Column(name = "SUM", columnDefinition = "FLOAT")
	private Double sum;
	
	@Column(name = "NOTE", columnDefinition = "NVARCHAR(255)")
	private String note;
	
//	@ManyToOne(cascade = CascadeType.ALL)
//	@JoinColumn(name = "CLASS_BATCH_ID")
//	private ClassBatch classBatch;

}

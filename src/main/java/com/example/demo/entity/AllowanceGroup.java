package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "allowance" }, callSuper = false)
@EqualsAndHashCode(exclude = { "allowance" }, callSuper = false)
@Entity
@Table(name = "ALLOWANCE_GROUP")
public class AllowanceGroup extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALLOWANCE_GROUP_ID", columnDefinition = "INT")
	private Integer id;
	
	@OneToOne
	@JoinColumn(name = "ALLOWANCE_ID")
	private Allowance allowance;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}

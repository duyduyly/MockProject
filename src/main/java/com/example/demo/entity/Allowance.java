package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "allowanceGroup", "trainee" })
@EqualsAndHashCode(exclude = { "allowanceGroup", "trainee" }, callSuper = false)
@Entity
@Table(name = "ALLOWANCE")
public class Allowance extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ALLOWANCE_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "CLASS_ID", columnDefinition = "INT")
	@Range(min = 0, message = "CLASS ID must be greater than 0")
	private Integer classId;

	@Column(name = "ALLOWANCE_RESULT", columnDefinition = "INT")
	@Range(min = 0, message = "Allowance result must be greater than 0")
	private Integer allowanceResult;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@OneToOne(mappedBy = "allowance")
	private AllowanceGroup allowanceGroup;

	@ManyToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_ID")
	private Trainee trainee;
	
	@OneToOne(mappedBy = "allowance")
	private GPA gpa;
	
	//Lien het 2 cai nay voi nhau hoi ki
//	private ClassBatch classBatch;

}

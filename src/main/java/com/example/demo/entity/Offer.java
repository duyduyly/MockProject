package com.example.demo.entity;

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
@ToString(exclude = { "candidate" }, callSuper = false)
@EqualsAndHashCode(exclude = { "candidate" }, callSuper = false)
@Entity
@Table(name = "OFFER")
public class Offer extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OFFER_ID", columnDefinition = "INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;
	
	@Column(name = "JOB_RANK", columnDefinition = "INT")
	private Integer jobRank;
	
	@Column(name = "TECHNOLOGY", columnDefinition = "NVARCHAR(255)")
	private String technology;
	
	@Column(name = "CONTRACT_TYPE", columnDefinition = "INT")
	private Integer contractType;
	
	@Column(name = "OFFER_SALARY", columnDefinition = "INT")
	private Integer offerSalary;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}

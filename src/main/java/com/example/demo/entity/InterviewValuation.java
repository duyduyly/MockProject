package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "trainee", "interviewer" }, callSuper = false)
@EqualsAndHashCode(exclude = { "trainee", "interviewer" }, callSuper = false)
@Entity
@Table(name = "INTERVIEW_VALUATION")
public class InterviewValuation extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERVIEW_VALUATION_ID", columnDefinition = "INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_ID")
	private Trainee trainee;
	
	@Temporal(TemporalType.DATE)
	@Column(name = "_DATE")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "TRAINER_ID")
	private Trainer interviewer;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	

}

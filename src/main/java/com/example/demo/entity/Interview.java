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
@ToString(exclude = { "candidate", "interviewer"}, callSuper = false)
@EqualsAndHashCode(exclude = { "candidate", "interviewer"}, callSuper = false)
@Entity
@Table(name = "INTERVIEW")
public class Interview extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "INTERVIEW_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "TIME", columnDefinition = "INT")
	private Integer time;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "TRAINER_ID")
	private Trainer interviewer;

	@Column(name = "COMMENTS", columnDefinition = "NVARCHAR(255)")
	private String comments;

	@Column(name = "RESULT", columnDefinition = "INT")
	private Integer result;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;

}

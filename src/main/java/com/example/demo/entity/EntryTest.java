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
@ToString(exclude = { "candidate", "technicalValuator", "languageValuator" }, callSuper = false)
@EqualsAndHashCode(exclude = { "candidate", "technicalValuator", "languageValuator" }, callSuper = false)
@Entity
@Table(name = "ENTRY_TEST")
public class EntryTest extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ENTRY_TEST_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "TIME", columnDefinition = "INT")
	private Integer time;

	@Temporal(TemporalType.DATE)
	@Column(name = "DATE")
	private Date date;

	@ManyToOne
	@JoinColumn(name = "TECHNICAL_VALUATOR_ID")
	private Trainer technicalValuator;

	@ManyToOne
	@JoinColumn(name = "LANGUAGE_VALUATOR_ID")
	private Trainer languageValuator;

	@Column(name = "TECHNICAL_RESULT", columnDefinition = "INT")
	private Integer technicalResult;

	@Column(name = "LANGUAGE_RESULT", columnDefinition = "INT")
	private Integer languageResult;

	@Column(name = "RESULT", columnDefinition = "INT")
	private Integer result;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@ManyToOne
	@JoinColumn(name = "CANDIDATE_ID")
	private Candidate candidate;
}

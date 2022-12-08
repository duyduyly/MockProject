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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "trainee" }, callSuper = false)
@EqualsAndHashCode(exclude = { "trainee" }, callSuper = false)
@Entity
@Table(name = "ATTENDANT_STATUS")
public class AttendantStatus extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ATTENDANT_STATUS_ID", columnDefinition = "INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_ID")
	private Trainee trainee;
	
	@Column(name = "DISCIPLINE_POINT", columnDefinition = "INT")
	private Integer disciplinePoint;
	
	@Column(name = "MILESTONES", columnDefinition = "INT")
	private Integer milestones;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@OneToOne(mappedBy = "attendantStatus")
	private GPA gpa;
	
	

}

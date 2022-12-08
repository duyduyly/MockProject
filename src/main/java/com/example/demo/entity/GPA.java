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
@ToString(exclude = { "trainee", "allowance", "attendantStatus" }, callSuper = false)
@EqualsAndHashCode(exclude = { "trainee", "allowance", "attendantStatus" }, callSuper = false)
@Entity
@Table(name = "GPA")
public class GPA extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "GPA_ID", columnDefinition = "INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_ID")
	private Trainee trainee;
	
	//Co le khong can lien ket ClassBatch va GPA, ko biêt nua :((
//	private ClassBatch classBatch;
	
	@OneToOne
	@JoinColumn(name = "ATTENDANT_STATUS_ID")
	private AttendantStatus attendantStatus;
	
	@OneToOne
	@JoinColumn(name = "ALLOWANCE_ID")
	private Allowance allowance;
	
	@Column(name = "GPA_RESULT", columnDefinition = "INT")
	private Integer gpaResult;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}

package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfMilestoneConfigurations", "setOfAllowances", "status", "setOfGPAs",
		"setOfRewardPenalties", "setOfGuarantees", "setOfInterviewValuations" })
@EqualsAndHashCode(exclude = { "setOfMilestoneConfigurations", "setOfAllowances", "status", "setOfGPAs",
		"setOfRewardPenalties", "setOfGuarantees", "setOfInterviewValuations" }, callSuper = false)
@Entity
@Table(name = "TRAINEE")
public class Trainee extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINEE_CANDIDATE_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "ACCOUNT", columnDefinition = "NVARCHAR(255)")
	private String account;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@OneToMany(mappedBy = "trainee")
	private Set<MilestoneConfiguration> setOfMilestoneConfigurations;

	@OneToMany(mappedBy = "trainee")
	private Set<Allowance> setOfAllowances;

	@ManyToOne
	@JoinColumn(name = "STATUS_ID")
	private Status status;
	
	@Column(name = "STATUS_IN_CLASS", columnDefinition = "NVARCHAR(255)")
	private String statusInClass;

	@OneToMany(mappedBy = "trainee")
	private Set<GPA> setOfGPAs;

	@OneToMany(mappedBy = "trainee")
	private Set<RewardPenalty> setOfRewardPenalties;

	@OneToMany(mappedBy = "trainee")
	private Set<Guarantee> setOfGuarantees;
	
	@OneToMany(mappedBy = "trainee")
	private Set<AttendantStatus> setOfAttendantStatus;
	
	@OneToMany(mappedBy = "trainee")
	private Set<InterviewValuation> setOfInterviewValuations;
	
	@ManyToOne
	@JoinColumn(name = "CLASS_BATCH_ID")
	private ClassBatch classBatch;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRAINEE_CANDIDATE_PROFILE_ID")
	private TraineeCandidateProfile traineeCandidateProfile;
	
	@Column(name = "HISTORY", columnDefinition = "NVARCHAR(255)")
	private String history;
	

}

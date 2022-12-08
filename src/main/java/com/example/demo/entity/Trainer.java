package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "trainerProfile", "setOfClassBatches", "setOfInterviews", "setOfInterviewValuations",
		"setOfAuditsInCharge", "setOfTechnicalEntryTests", "setOfLanguageEntryTests", "setOfAuditsRelatedTo", "setOfMasterClassBatches" })
@EqualsAndHashCode(exclude = { "trainerProfile", "setOfClassBatches", "setOfInterviews", "setOfInterviewValuations",
		"setOfAuditsInCharge", "setOfTechnicalEntryTests", "setOfLanguageEntryTests",
		"setOfAuditsRelatedTo" , "setOfMasterClassBatches"}, callSuper = false)
@Entity
@Table(name = "TRAINER")
@NamedNativeQueries({
		@NamedNativeQuery(name = "FIND_TRAINER_BY_USERNAME", query = "SELECT * FROM TRAINER WHERE USER_NAME = :userName", resultClass = Trainer.class),
		@NamedNativeQuery(name = "FIND_TRAINER_BY_USERNAME_PASSWORD", query = "SELECT * FROM TRAINER WHERE USER_NAME = :userName AND PASSWORD = :password", resultClass = Trainer.class), })
public class Trainer extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINER_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "ACCOUNT", columnDefinition = "NVARCHAR(255)")
	private String account;

	@Column(name = "TYPE", columnDefinition = "INT")
	@Range(min = 0, message = "Trainer type cannot be null or smaller than 0")
	private Integer type;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "TRAINER_PROFILE_ID")
	private TrainerProfile trainerProfile;

//	@ManyToMany
//	private Set<ClassBatch> setOfClassBatches;
	
	@OneToMany(mappedBy = "masterTrainer")
	private Set<ClassBatch> setOfMasterClassBatches;

	@OneToMany(mappedBy = "interviewer")
	private Set<Interview> setOfInterviews;

	@OneToMany(mappedBy = "interviewer")
	private Set<InterviewValuation> setOfInterviewValuations;

	@OneToMany(mappedBy = "pic")
	private Set<Audit> setOfAuditsInCharge;

	@OneToMany(mappedBy = "relatedPartyPeople")
	private Set<Audit> setOfAuditsRelatedTo;

	@OneToMany(mappedBy = "technicalValuator")
	private Set<EntryTest> setOfTechnicalEntryTests;

	@OneToMany(mappedBy = "languageValuator")
	private Set<EntryTest> setOfLanguageEntryTests;

}

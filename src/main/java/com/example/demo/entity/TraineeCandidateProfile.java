package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "trainee" }, callSuper = false)
@EqualsAndHashCode(exclude = { "trainee" }, callSuper = false)
@Entity
@Table(name = "TRAINEE_CANDIDATE_PROFILE")
public class TraineeCandidateProfile extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINEE_CANDIDATE_PROFILE_ID", columnDefinition = "INT")
	private Integer id;

	@OneToOne(mappedBy = "traineeCandidateProfile")
	private Trainee trainee;
	
	@OneToOne(mappedBy = "traineeCandidateProfile")
	private Candidate candidate;

	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
//	@NotEmpty(message = "Fullname is not empty")
	private String fullName;

	@Temporal(TemporalType.DATE)
	@Past(message = "Birthdate cannot be in the future")
	@Column(name = "DATE_OF_BIRTH")
//	@NotEmpty(message = "DOB is not empty")
	private Date dateOfBirth;

	@Column(name = "GENDER", columnDefinition = "NVARCHAR(255)")
//	@NotEmpty(message = "Gender is not empty")
	private String gender;

	@ManyToOne
	@JoinColumn(name = "UNIVERSITY_ID")
//	@NotEmpty(message = "University is not empty")
	private University university;

	@ManyToOne
	@JoinColumn(name = "FACULTY_ID")
//	@NotEmpty(message = "Faculty is not empty")
	private Faculty faculty;

	@Column(name = "GRADUATION_YEAR")
	private Date graduationYear;

	@Column(name = "PHONE", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Phone cannot be empty")
	private String phone;

	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@Column(name = "TYPE", columnDefinition = "NVARCHAR(255)")
	private String type;

	@Column(name = "SKILL", columnDefinition = "NVARCHAR(255)")
	private String skill;

	@Column(name = "FOREIGN_LANGUAGE", columnDefinition = "NVARCHAR(255)")
	private String foreignLanguage;

	@Column(name = "LEVEL", columnDefinition = "NVARCHAR(255)")
	private String level;

	@Column(name = "CV", columnDefinition = "NVARCHAR(255)")
	private String CV;

	@Column(name = "ALLOCATION_STATUS", columnDefinition = "NVARCHAR(255)")
	private String allocationStatus;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

}

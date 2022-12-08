package com.example.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@EqualsAndHashCode(exclude = { "trainer" }, callSuper = false)
@Entity
@Table(name = "TRAINER_PROFILE")
public class TrainerProfile extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TRAINER_PROFILE_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message = "Fullname cannot be empty")
	private String fullName;

	@Temporal(TemporalType.DATE)
	@Past(message = "Birthdate cannot be in the future")
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;

	@Column(name = "GENDER", columnDefinition = "NVARCHAR(255)")
	private String gender;

	@Column(name = "UNIT", columnDefinition = "INT")
	@Range(min = 0, message = "Unit cannot be empty")
	private Integer unit;

	@Column(name = "MAJOR", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message = "Major cannot be empty")
	private String major;

	@Column(name = "PHONE", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Phone cannot be empty")
	private String phone;

	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Email cannot be empty")
	private String email;

	@Column(name = "EXPERIENCE", columnDefinition = "INT")
	@Range(min = 0, message = "Major cannot be empty")
	private Integer experience;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@OneToOne(mappedBy = "trainerProfile")
	private Trainer trainer;

}

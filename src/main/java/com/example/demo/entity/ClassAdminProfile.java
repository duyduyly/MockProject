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

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "classAdmin" }, callSuper = false)
@EqualsAndHashCode(exclude = { "classAdmin" }, callSuper = false)
@Entity
@Table(name = "CLASS_ADMIN_PROFILE")
public class ClassAdminProfile extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLASS_ADMIN_PROFILE_ID", columnDefinition = "INT")
	private Integer id;
	
	@OneToOne(mappedBy = "classAdminProfile")
	private ClassAdmin classAdmin;
	
	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message = "Fullname cannot be empty")
	private String fullName;
	
	@Temporal(TemporalType.DATE)
	@Past(message = "Birthdate cannot be in the future")
	@Column(name = "DATE_OF_BIRTH")
	private Date dateOfBirth;
	
	@Column(name = "GENDER", columnDefinition = "NVARCHAR(255)")
	private String gender;
	
	@Column(name = "PHONE", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Phone cannot be empty")
	private String phone;

	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(255)", unique = true)
	private String email;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	

}

package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfTraineeCandidateProfiles" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfTraineeCandidateProfiles" }, callSuper = false)
@Entity
@Table(name = "FACULTY")
public class Faculty extends BaseEntity {

	
	private static final long serialVersionUID = 1L;
	
	
	
	public Faculty(String facultyName) {
		super();
		this.facultyName = facultyName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FACULTY_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "FACULTY_NAME", columnDefinition = "NVARCHAR(255)")
	private String facultyName;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@OneToMany(mappedBy = "faculty")
	private Set<TraineeCandidateProfile> setOfTraineeCandidateProfiles;

}

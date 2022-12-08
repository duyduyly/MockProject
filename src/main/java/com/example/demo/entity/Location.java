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
@ToString(exclude = { "setOfClassBatches", "setOfCandidates" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfClassBatches", "setOfCandidates" }, callSuper = false)
@Entity
@Table(name = "LOCATION")
public class Location extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Location(String locationName) {
		super();
		this.locationName = locationName;
	}
	
	public Location(String locationName, String acronym) {
		super();
		this.locationName = locationName;
		this.acronym = acronym;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LOCATION_ID", columnDefinition = "INT")
	private Integer id;

	@OneToMany(mappedBy = "location")
	private Set<ClassBatch> setOfClassBatches;
	
	@OneToMany(mappedBy = "location")
	private Set<Candidate> setOfCandidates;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@Column(name = "LOCATION_NAME", columnDefinition = "NVARCHAR(255)")
	private String locationName;
	
	@Column(name = "ACRONYM", columnDefinition = "NVARCHAR(255)")
	private String acronym;

//	@OneToOne(mappedBy = "location")
//	private Candidate candidate;

}

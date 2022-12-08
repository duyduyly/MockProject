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
@ToString(exclude = { "setOfClassBatches"}, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfClassBatches"}, callSuper = false)
@Entity
@Table(name = "SUBJECT_TYPE")
public class SubjectType extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public SubjectType(String subjectTypeName) {
		super();
		this.subjectTypeName = subjectTypeName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SUBJECT_TYPE_ID", columnDefinition = "INT")
	private Integer id;
	
	@OneToMany(mappedBy = "subjectType")
	private Set<ClassBatch> setOfClassBatches;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "SUBJECT_TYPE_NAME", columnDefinition = "NVARCHAR(255)")
	private String subjectTypeName;

}

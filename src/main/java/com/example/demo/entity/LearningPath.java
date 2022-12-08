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
@Table(name = "LEARNING_PATH")
public class LearningPath extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEARNING_PATH_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "LEARNING_PATH_NAME", columnDefinition = "NVARCHAR(255)")
	private String learningPathName;
	
	@Column(name = "DETAILED_INFORMATION", columnDefinition = "NVARCHAR(255)")
	private String detailedInformation;
	
	@Column(name = "FILE_ADDRESS", columnDefinition = "NVARCHAR(MAX)")
	private String learningPathFileAddress;
	
	@Column(name = "TYPE_OF_CLASS", columnDefinition = "NVARCHAR(MAX)")
	private String tyOfClass;
	
	@Column(name = "SKILL", columnDefinition = "NVARCHAR(MAX)")
	private String skill;
	
	@Column(name = "POSITION_OF_SKILL", columnDefinition = "NVARCHAR(MAX)")
	private String positionOfSkill;
	
	
	
	@OneToMany(mappedBy = "learningPath")
	private Set<ClassBatch> setOfClassBatches;

}

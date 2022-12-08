package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfMilestoneConfigurations" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfMilestoneConfigurations" }, callSuper = false)
@Entity
@Table(name = "TOPIC")
public class Topic extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TOPIC_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message = "Fullname cannot be empty")
	private String topicName;
	
	@OneToMany(mappedBy = "topic")
	private Set<MilestoneConfiguration> setOfMilestoneConfigurations;
	
	

}

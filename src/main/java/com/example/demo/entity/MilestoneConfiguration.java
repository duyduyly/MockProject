package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "topic", "trainee" }, callSuper = false)
@EqualsAndHashCode(exclude = { "topic", "trainee" }, callSuper = false)
@Entity
@Table(name = "MILESTONE_CONFIGURATION")
public class MilestoneConfiguration extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "MILESTONE_CONFIGURATION_ID", columnDefinition = "INT")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "TOPIC_ID")
	private Topic topic;
	
	@ManyToOne
	@JoinColumn(name = "TRAINEE_CANDIDATE_ID")
	private Trainee trainee;

}

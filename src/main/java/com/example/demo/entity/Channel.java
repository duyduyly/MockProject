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
@ToString(exclude = { "candidate" }, callSuper = false)
@EqualsAndHashCode(exclude = { "candidate" }, callSuper = false)
@Entity
@Table(name = "CHANNEL")
public class Channel extends BaseEntity {

	public Channel(String channelName) {
		this.channelName = channelName;
	}

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CHANNEL_ID", columnDefinition = "INT")
	private Integer id;
	
	private Candidate candidate;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "CHANNEL_NAME", columnDefinition = "NVARCHAR(255)")
	private String channelName;

}

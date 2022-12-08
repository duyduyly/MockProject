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
@ToString(exclude = { "setOfTrainees" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfTrainees" }, callSuper = false)
@Entity
@Table(name = "STATUS")
public class Status extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Status(String statusName) {
		super();
		this.statusName = statusName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "STATUS_ID", columnDefinition = "INT")
	private Integer id;

	@OneToMany(mappedBy = "status")
	private Set<Trainee> setOfTrainees;

	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;

	@Column(name = "STATUS_NAME", columnDefinition = "NVARCHAR(255)")
	private String statusName;

//	private ClassBatch classBatch;

}

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
@Table(name = "SCOPE")
public class Scope extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public Scope(String scopeName) {
		super();
		this.scopeName = scopeName;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCOPE_ID", columnDefinition = "INT")
	private Integer id;
	
	@OneToMany(mappedBy = "scope")
	private Set<ClassBatch> setOfClassBatches;
	
	@Column(name = "REMARKS", columnDefinition = "NVARCHAR(255)")
	private String remarks;
	
	@Column(name = "SCOPE_NAME", columnDefinition = "NVARCHAR(255)")
	private String scopeName;

}

package com.example.demo.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = {})
@EqualsAndHashCode(exclude = {}, callSuper = false)
@Entity
@Table(name = "FA_MANAGER")
public class FAManager extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "FA_MANAGER_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "FULLNAME", columnDefinition = "NVARCHAR(255)")
	private String fullName;
	
	@Column(name = "ACCOUNT", columnDefinition = "NVARCHAR(255)")
	private String account;
	
	@Column(name = "EMAIL", columnDefinition = "NVARCHAR(255)")
	private String email;

}

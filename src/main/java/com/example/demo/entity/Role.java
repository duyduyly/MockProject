package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(exclude = "setOfUsers", callSuper = false)
@NoArgsConstructor
@Entity
@Table(name = "ROLE")
public class Role extends BaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROLE_ID", columnDefinition = "INT")
	private Integer id;
	
	@Column(name = "ROLE_NAME", columnDefinition = "NVARCHAR(255)", unique = true)
	private String name;
	
	@ManyToMany(mappedBy = "setOfRoles")
	private Set<User> setOfUsers;

}

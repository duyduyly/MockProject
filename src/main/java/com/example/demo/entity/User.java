package com.example.demo.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(exclude = { "setOfRoles" }, callSuper = false)
@EqualsAndHashCode(exclude = { "setOfRoles" }, callSuper = false)
@Entity
@Table(name = "_USER")
public class User extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID", columnDefinition = "INT")
	private Integer id;

	@Column(name = "USER_NAME", columnDefinition = "NVARCHAR(255)", unique = true)
	@NotEmpty(message = "Username cannot be empty")
	private String username;

	@Column(name = "PASSWORD", columnDefinition = "NVARCHAR(255)")
	@NotEmpty(message = "Password cannot be empty")
	private String password;

	@ManyToMany
	private Set<Role> setOfRoles;

}

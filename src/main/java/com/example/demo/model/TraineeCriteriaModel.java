package com.example.demo.model;

import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TraineeCriteriaModel extends BaseEntity {/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String emplId;
	private String dateOfBirth;
	private String account;
	private String name;
	private String phone;
	private String email;

}

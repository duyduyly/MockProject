package com.example.demo.model;

import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CandidateCriteriaModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String fullName; // not trainee
	private String dateOfBirth; // not trainee
	private String phone; // not trainee
	private String email; // not trainee
	private String account; // not
	
	// Candidate view in create page
	private String type;
	private String status;
	private String applicationDate;
	private String channel; // not
	private String location;
	private String gender; // not trainee
	private String university; // not trainee
	private String faculty; // not trainee
	private String skill;
	private String graduationYear;
	private String foreignLanguage;
	private String level;
	private String note;
	private String history;
	private String cv;
}

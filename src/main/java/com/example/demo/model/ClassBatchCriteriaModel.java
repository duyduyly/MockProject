package com.example.demo.model;

import com.example.demo.entity.BaseEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassBatchCriteriaModel extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String locationName;
	
	private String className;
	
	private String classStatus;
	
	private String fromDate;
	
	private String toDate;

}

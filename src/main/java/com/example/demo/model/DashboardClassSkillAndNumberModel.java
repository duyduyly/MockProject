package com.example.demo.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DashboardClassSkillAndNumberModel {
	
	private String skillName;
	private Long numberOfClass;
	
	public DashboardClassSkillAndNumberModel(String skillName, Long numberOfClass) {
		super();
		this.skillName = skillName;
		this.numberOfClass = numberOfClass;
	}
	
	public static Long getTotalNumberOfClasses(List<DashboardClassSkillAndNumberModel> listOfModels) {
		Long totalNumberOfClasses = 0L;
		for (DashboardClassSkillAndNumberModel model : listOfModels) {
			totalNumberOfClasses = totalNumberOfClasses + model.getNumberOfClass();
		}
		return totalNumberOfClasses;
	}
}

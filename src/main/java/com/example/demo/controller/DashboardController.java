package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DashboardClassSkillAndNumberModel;
import com.example.demo.service.iservice.IClassBatchService;
import com.example.demo.service.iservice.IClassStatusService;
import com.example.demo.service.iservice.ILearningPathService;
import com.example.demo.service.iservice.ILocationService;

@Controller
public class DashboardController {
	
	@Autowired
	private ILearningPathService learningPathService;
	
	@Autowired
	private IClassBatchService classBatchService;
	
	@Autowired
	private ILocationService locationService;
	
	@Autowired
	private IClassStatusService classStatusService;

	@RequestMapping(path = { "/", "/dashboard" }, method = RequestMethod.GET)
	public String getDashboardPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString) {
		
		List<DashboardClassSkillAndNumberModel> planningTable = new ArrayList<DashboardClassSkillAndNumberModel>();
		List<DashboardClassSkillAndNumberModel> plannedTable = new ArrayList<DashboardClassSkillAndNumberModel>();
		List<DashboardClassSkillAndNumberModel> inProgressTable = new ArrayList<DashboardClassSkillAndNumberModel>();
		
		for (String skill : learningPathService.findAllSkills()) {
			planningTable.add(new DashboardClassSkillAndNumberModel(skill, classBatchService
					.countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(skill, "Planning")));
			plannedTable.add(new DashboardClassSkillAndNumberModel(skill, classBatchService
					.countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(skill, "Planned")));
			inProgressTable.add(new DashboardClassSkillAndNumberModel(skill, classBatchService
					.countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(skill, "In-progress")));
		}
		
		Long totalNumberOfPlanningClasses = DashboardClassSkillAndNumberModel.getTotalNumberOfClasses(planningTable);
		Long totalNumberOfPlannedClasses = DashboardClassSkillAndNumberModel.getTotalNumberOfClasses(plannedTable);
		Long totalNumberOfInProgressClasses = DashboardClassSkillAndNumberModel.getTotalNumberOfClasses(inProgressTable);
		
		model.addAttribute("listOfLocations", locationService.findAll());
		model.addAttribute("listOfClassStatuses", classStatusService.findAll());
		model.addAttribute("listOfPlanningClasses", planningTable);
		model.addAttribute("listOfPlannedClasses", plannedTable);
		model.addAttribute("listOfInProgressClasses", inProgressTable);
		model.addAttribute("totalNumberOfPlanningClasses", totalNumberOfPlanningClasses);
		model.addAttribute("totalNumberOfPlannedClasses", totalNumberOfPlannedClasses);
		model.addAttribute("totalNumberOfInProgressClasses", totalNumberOfInProgressClasses);
		model.addAttribute("errorString", errorString);
		
		return "dashboard";
	}

}

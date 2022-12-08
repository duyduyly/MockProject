package com.example.demo.service.iservice;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.entity.ClassBatch;
import com.example.demo.model.ClassBatchCriteriaModel;

public interface IClassBatchService {

	public List<ClassBatch> filterClassSearchCriteria(ClassBatchCriteriaModel classBatchCriteriaModel);

	public boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage, String remarksContent);

	public Integer calculateClassBatchActualTraineeNumber(ClassBatch classBatch);

	public List<ClassBatch> findAll();

	public void save(ClassBatch classBatch);
	
	public ClassBatch findByClassName(String className);

	public ClassBatch findByClassCode(String classCode);

	public List<ClassBatch> findAllBySearchCriteria(
			String locationName, String classStatusName, String className, Date fromDate, Date toDate);
	
	public Long countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(String skill, String classStatusName);

	public Optional<ClassBatch> findById(Integer id);
	
	public Set<String> findAllClassName();

}

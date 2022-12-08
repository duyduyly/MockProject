package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassBatch;
import com.example.demo.model.ClassBatchCriteriaModel;
import com.example.demo.repository.ClassBatchRepository;
import com.example.demo.service.iservice.IClassBatchService;
import com.example.demo.service.iservice.IClassStatusService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.utils.DateUtils;

@Service
public class ClassBatchService implements IClassBatchService {

	@Autowired
	private ClassBatchRepository classBatchRepository;

	@Autowired
	private IClassStatusService classStatusService;

	@Autowired
	private ITraineeService traineeService;

	@Override
	public List<ClassBatch> filterClassSearchCriteria(ClassBatchCriteriaModel classBatchCriteriaModel) {
		Date toDate = null;
		Date fromDate = null;

		if (Objects.isNull(classBatchCriteriaModel.getFromDate()) || classBatchCriteriaModel.getFromDate().isEmpty()
				|| classBatchCriteriaModel.getFromDate().isBlank()) {
		} else {
			fromDate = DateUtils.parseDDMMYYYYDateFromString(classBatchCriteriaModel.getFromDate());
		}
		if (Objects.isNull(classBatchCriteriaModel.getToDate()) || classBatchCriteriaModel.getToDate().isEmpty()
				|| classBatchCriteriaModel.getToDate().isBlank()) {
		} else {
			toDate = DateUtils.parseDDMMYYYYDateFromString(classBatchCriteriaModel.getToDate());
		}

		if (Objects.equals(classBatchCriteriaModel.getLocationName(), "All")) {
			classBatchCriteriaModel.setLocationName(null);
		}

		if (Objects.equals(classBatchCriteriaModel.getClassStatus(), "All")) {
			classBatchCriteriaModel.setClassStatus(null);
		}

		if (Objects.equals(classBatchCriteriaModel.getClassName(), "All")) {
			classBatchCriteriaModel.setClassName(null);
		}
		return classBatchRepository.findAllBySearchCriteria(classBatchCriteriaModel.getLocationName(),
				classBatchCriteriaModel.getClassStatus(), classBatchCriteriaModel.getClassName(), fromDate, toDate);
	}

	@Override
	public boolean changeClassProgression(String classBatchId, List<String> requiredStatuses, String newStatus,
			String userFullName, String completeMessage, String remarksContent) {
		ClassBatch classBatch = classBatchRepository.findById(Integer.valueOf(classBatchId)).get();
		for (String status : requiredStatuses) {
			if (Objects.equals(classBatch.getClassStatus().getClassStatusName(), status)) {
				classBatch.setClassStatus(classStatusService.findByClassStatusName(newStatus));
				classBatch.setHistory(String.valueOf(new Date()) + " - " + completeMessage + " by " + userFullName
						+ " - " + remarksContent);
				if (Objects.equals(completeMessage, "Started")) {
					classBatch.setActualStartDate(new Date());
					System.out
							.println("Set class's actual start date to " + classBatch.getActualStartDate().toString());
				}
				if (Objects.equals(completeMessage, "Finished")) {
					classBatch.setActualEndDate(new Date());
					System.out.println("Set class's actual end date to " + classBatch.getActualEndDate().toString());
				}
				classBatchRepository.save(classBatch);
				System.out.println("Class " + classBatch.getClassCode() + completeMessage + " by " + userFullName);
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer calculateClassBatchActualTraineeNumber(ClassBatch classBatch) {
		Long countTrainee = traineeService.countByStatusInClassIsAndClassBatch_IdIs("Active", classBatch.getId());
		Integer actualTraineeNumber = countTrainee == null ? null : Math.toIntExact(countTrainee);
		return actualTraineeNumber;
	}

	@Override
	public List<ClassBatch> findAll() {
		return classBatchRepository.findAll();
	}

	@Override
	public void save(ClassBatch classBatch) {
		classBatchRepository.save(classBatch);
	}

	@Override
	public ClassBatch findByClassName(String className) {
		return classBatchRepository.findByClassName(className);
	}

	@Override
	public ClassBatch findByClassCode(String classCode) {
		return classBatchRepository.findByClassCode(classCode);
	}

	@Override
	public List<ClassBatch> findAllBySearchCriteria(String locationName, String classStatusName, String className,
			Date fromDate, Date toDate) {
		return classBatchRepository.findAllBySearchCriteria(locationName, classStatusName, className, fromDate, toDate);
	}

	@Override
	public Long countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(String skill,
			String classStatusName) {
		return classBatchRepository.countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(skill,
				classStatusName);
	}

	@Override
	public Optional<ClassBatch> findById(Integer id) {
		return classBatchRepository.findById(id);
	}

	@Override
	public Set<String> findAllClassName() {
		return classBatchRepository.findAllClassName();
	}

}

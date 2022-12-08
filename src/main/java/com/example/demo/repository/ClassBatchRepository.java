package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassBatch;

@Repository
public interface ClassBatchRepository extends JpaRepository<ClassBatch, Integer> {

	ClassBatch findByClassName(String className);

	ClassBatch findByClassCode(String classCode);

	List<ClassBatch> findAllByLocation_LocationNameIsAndClassNameIsAndClassStatus_ClassStatusNameIsAndActualStartDateAfterAndActualEndDateBefore(
			String locationName, String className, String classStatusName, Date fromDate, Date toDate);

	List<ClassBatch> findAllByClassName(String className);

	List<ClassBatch> findAllByLocation_LocationName(String locationName);

	List<ClassBatch> findAllByClassStatus_ClassStatusName(String classStatusName);

	List<ClassBatch> findAllByActualStartDateAfter(Date fromDate);

	List<ClassBatch> findAllByActualEndDateBefore(Date toDate);

	List<ClassBatch> findAllByActualStartDateAfterAndActualEndDateBefore(Date fromDate, Date toDate);

	@Query("SELECT c FROM ClassBatch c WHERE (:locationName is null or c.location.locationName = :locationName) AND"
			+ " (:classStatusName is null or c.classStatus.classStatusName = :classStatusName) AND"
			+ " (:className is null or c.className = :className) AND"
			+ " (:fromDate is null or c.actualStartDate <= :fromDate) AND"
			+ " (:toDate is null or c.actualEndDate >= :toDate)")
	List<ClassBatch> findAllBySearchCriteria(
			String locationName, String classStatusName, String className, Date fromDate, Date toDate);
	
	Long countByLearningPath_SkillContainingAndClassStatus_ClassStatusNameContaining(String skill, String classStatusName);
	
	@Query("SELECT c.className FROM ClassBatch c")
	Set<String> findAllClassName();
	
}

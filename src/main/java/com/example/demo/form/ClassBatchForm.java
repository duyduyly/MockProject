package com.example.demo.form;

import java.util.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.entity.Audit;
import com.example.demo.entity.Budget;
import com.example.demo.entity.BudgetDetail;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.DeliveryType;
import com.example.demo.entity.FormatType;
import com.example.demo.entity.LearningPath;
import com.example.demo.entity.Location;
import com.example.demo.entity.Scope;
import com.example.demo.entity.SubSubjectType;
import com.example.demo.entity.SubjectType;
import com.example.demo.entity.SupplierPartner;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.Trainer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassBatchForm {

	
	private Integer id;
	
	private String className;
	
	private String classCode;
	
	private Budget budget;
	
	private Location location;
	
	private SubjectType subjectType;
	
	private SubSubjectType subSubjectType;
	
	private DeliveryType deliveryType;
	
	private FormatType formatType;
	
	private Scope scope;
	
	private ClassStatus classStatus;
	
	private SupplierPartner supplierPartner;
	
	private Audit audit;
	
	private ClassAdmin classAdmin;
	
	private String detailLocation;

	private Integer estimatedBudget;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expectedStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date expectedEndDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actualStartDate;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date actualEndDate;
	
	private Integer plannedTraineeNumber;
	
	private Integer acceptedTraineeNumber;
	
	private Integer actualTraineeNumber;
	
	private Set<Trainer> setOfTrainers;
	
	private Trainer masterTrainer;
	
	private Integer milestones;
	
	private Set<Trainee> setOfTrainees;
	
	private String status;
	
	private String remarks;
	
	private String history;
	
	private LearningPath learningPath;
	
	private String curriculum;
	
	private Set<BudgetDetail> setOfBudgetDetails;
	
	//set value from classBatch to  classBatchForm
			public ClassBatchForm(ClassBatch form) {
				this.setId(form.getId());
				this.setClassName(form.getClassName());
				this.setClassCode(form.getClassCode());
				this.setBudget(form.getBudget());
				this.setLocation(form.getLocation());
				this.setSubjectType(form.getSubjectType());
				this.setSubSubjectType(form.getSubSubjectType());
				this.setDeliveryType(form.getDeliveryType());
				this.setFormatType(form.getFormatType());
				this.setScope(form.getScope());
				this.setClassStatus(form.getClassStatus());
				this.setSupplierPartner(form.getSupplierPartner());
				this.setAudit(form.getAudit());
				this.setClassAdmin(form.getClassAdmin());
				this.setDetailLocation(form.getDetailLocation());
				this.setEstimatedBudget(form.getEstimatedBudget());
				this.setExpectedStartDate(form.getExpectedStartDate());
				this.setExpectedEndDate(form.getExpectedEndDate());
				this.setActualStartDate(form.getActualStartDate());
				this.setActualEndDate(form.getActualEndDate());
				this.setPlannedTraineeNumber(form.getPlannedTraineeNumber());
				this.setAcceptedTraineeNumber(form.getAcceptedTraineeNumber());
				this.setActualTraineeNumber(form.getActualTraineeNumber());
				this.setSetOfTrainers(form.getSetOfTrainers());
				this.setMasterTrainer(form.getMasterTrainer());
				this.setMilestones(form.getMilestones());
				this.setSetOfTrainees(form.getSetOfTrainees());
				this.setStatus(form.getStatus());
				this.setRemarks(form.getRemarks());
				this.setHistory(form.getHistory());
				this.setLearningPath(form.getLearningPath());
				this.setSetOfBudgetDetails(form.getSetOfBudgetDetails());
				this.setCurriculum(form.getCurriculum());
				//thiếu curiculum vì sẽ lấy từ form và set vào
			}
	
}

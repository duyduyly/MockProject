package com.example.demo.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;
import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.BudgetDetail;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.Trainer;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClassBatchViewModel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// General
	private Integer id;
	private String classCode;
	private String className;
	private String classStatus;
	private Integer plannedTraineeNo;
	private Integer acceptedTraineeNo;
	private Integer actualTraineeNo;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date expectedStartDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date expectedEndDate;
	private String locationName;
	private String detailedLocation;
	private String budgetCode;
	private Integer estimatedBudget;
	private String classAdmin;
	private String learningPath;
	private String history;

	// Detail
	private String subjectType;
	private String subSubjectType;
	private String deliveryType;
	private String formatType;
	private String scope;
	private String supplierPartner;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date actualStartDate;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date actualEndDate;
	private String masterTrainer;
	private String trainers;
	private String curriculum;
	private String remarks;

	// Budget
	private Double total;
	private String overBudget;
	// BudgetDetails
	private List<BudgetDetail> listOfBudgetDetails;

	// Audit
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date auditDate;
	private String eventCategory;
	private String relatedPeople;
	private Integer action;
	private String pic;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd-MMM-YYYY")
	private Date deadline;
	private String auditNote;

	public ClassBatchViewModel(ClassBatch a) {
		// General
		this.id = a.getId();
		this.className = a.getClassName();
		this.classCode = a.getClassCode();
		this.classStatus = a.getClassStatus().getClassStatusName();
		this.plannedTraineeNo = a.getPlannedTraineeNumber();
		this.acceptedTraineeNo = a.getAcceptedTraineeNumber();
		this.actualTraineeNo = a.getActualTraineeNumber();
		this.expectedStartDate = a.getExpectedStartDate();
		this.expectedEndDate = a.getExpectedEndDate();
		this.actualStartDate = a.getActualStartDate();
		this.actualEndDate = a.getActualEndDate();
		this.locationName = a.getLocation().getLocationName();
		this.detailedLocation = a.getDetailLocation();
		this.budgetCode = a.getBudget().getBudgetName();
		this.estimatedBudget = a.getEstimatedBudget();
		this.classAdmin = a.getClassAdmin().getAccount();
		this.learningPath = a.getLearningPath().getLearningPathName();
		this.history = a.getHistory();
		// Detail
		this.subjectType = a.getSubjectType().getSubjectTypeName();
		this.subSubjectType = a.getSubSubjectType().getSubSubjectTypeName();
		this.deliveryType = a.getDeliveryType().getDeliveryTypeName();
		this.formatType = a.getFormatType().getFormatTypeName();
		this.scope = a.getScope().getScopeName();
		this.supplierPartner = a.getSupplierPartner().getSupplierPartnerName();
		this.masterTrainer = a.getMasterTrainer().getAccount();
		this.trainers = generateTrainerAccountString(a.getSetOfTrainers());
		this.curriculum = "Not yet implemented!!!";
		this.remarks = a.getRemarks();
		//Budget
		this.listOfBudgetDetails = new ArrayList<BudgetDetail>(a.getSetOfBudgetDetails());
		this.total = calculateTotal(this.getListOfBudgetDetails());
		this.overBudget = isOverBudget(this);
		//Audit
		this.auditDate = a.getAudit().getDate();
		this.eventCategory = a.getAudit().getEventCategoty();
		this.relatedPeople = a.getAudit().getRelatedPartyPeople().getAccount();
		this.action = a.getAudit().getAction();
		this.pic = a.getAudit().getPic().getAccount();
		this.deadline = a.getAudit().getDeadline();
		this.auditNote = a.getAudit().getNote();
	}
	
	private String generateTrainerAccountString(Set<Trainer> setOfTrainers) {
		StringBuilder stringBuilder = new StringBuilder();
		for (Trainer trainer : setOfTrainers) {
			stringBuilder.append(trainer.getAccount()).append(", ");
		}
		stringBuilder.setLength(Math.max(stringBuilder.length() - 2, 0));
		return stringBuilder.toString();
	}
	
	private Double calculateTotal(List<BudgetDetail> listOfBudgetDetails) {
		Double total = 0d;
		for (BudgetDetail budgetDetail : listOfBudgetDetails) {
			total = total + budgetDetail.getSum();
		}
		return total;
	}
	
	private String isOverBudget(ClassBatchViewModel model) {
		Integer estimated = 0;
		if (!Objects.isNull(model.getEstimatedBudget())) {
			estimated = model.getEstimatedBudget();
		}
		Double total = calculateTotal(model.getListOfBudgetDetails());
		if (total > estimated) {
			return "Yes";
		} else {
			return "No";
		}
	}
}

package com.example.demo.controller.classmanagementcontroller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.ClassBatchProgression;
import com.example.demo.constants.DefaultPageSize;
import com.example.demo.entity.Audit;
import com.example.demo.entity.BudgetDetail;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.Location;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.TraineeCandidateProfile;
import com.example.demo.entity.University;
import com.example.demo.form.AuditFormOfClassBatch;
import com.example.demo.form.ClassBatchForm;
import com.example.demo.form.TraineeForm;
import com.example.demo.model.ClassBatchCriteriaModel;
import com.example.demo.model.ClassBatchViewModel;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.model.TraineeToChangeStatus;
import com.example.demo.service.iservice.IBudgetDetailService;
import com.example.demo.service.iservice.IBudgetService;
import com.example.demo.service.iservice.IClassAdminService;
import com.example.demo.service.iservice.IClassBatchService;
import com.example.demo.service.iservice.IClassStatusService;
import com.example.demo.service.iservice.IDeliveryTypeService;
import com.example.demo.service.iservice.IEmailService;
import com.example.demo.service.iservice.IFacultyService;
import com.example.demo.service.iservice.IFormatTypeService;
import com.example.demo.service.iservice.ILearningPathService;
import com.example.demo.service.iservice.ILocationService;
import com.example.demo.service.iservice.IPersonService;
import com.example.demo.service.iservice.IScopeService;
import com.example.demo.service.iservice.IStatusService;
import com.example.demo.service.iservice.ISubSubJectTypeService;
import com.example.demo.service.iservice.ISubjectTypeService;
import com.example.demo.service.iservice.ISupplierPartnerService;
import com.example.demo.service.iservice.ITraineeCandidateProfileService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.service.iservice.ITrainerService;
import com.example.demo.service.iservice.IUniversityService;
import com.example.demo.utils.PaginationUtils;
import com.example.demo.utils.ReadFileExcell;
import com.example.demo.utils.WebUtils;

@Controller
@RequestMapping("/classBatch")
public class ClassManagementController {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	HttpSession httpSession;

	@Autowired
	private IClassBatchService classBatchService;

	@Autowired
	private ITrainerService trainerService;

	@Autowired
	private IClassAdminService classAdminService;

	@Autowired
	private ILearningPathService learningPathService;

	@Autowired
	private IBudgetService budgetService;

	@Autowired
	private ISubjectTypeService subjectTypeService;

	@Autowired
	private IDeliveryTypeService deliveryTypeService;

	@Autowired
	private ISubSubJectTypeService subSubJectTypeService;

	@Autowired
	private IFormatTypeService formatTypeService;

	@Autowired
	private IScopeService scopeService;

	@Autowired
	private ILocationService locationService;

	@Autowired
	private ISupplierPartnerService supplierPartnerService;

	@Autowired
	private IClassStatusService classStatusService;

	@Autowired
	private IEmailService emailService;

	@Autowired
	private IUniversityService universityService;

	@Autowired
	private IFacultyService facultyService;
	
	@Autowired
	private IStatusService statusService;

	@Autowired
	private IPersonService personService;

	@Autowired
	private ITraineeCandidateProfileService traineeCandidateProfileService;

	@Autowired
	private ITraineeService traineeService;

	@Autowired
	private IBudgetDetailService budgetDetailService;

	private final String[] EVENT_CATEGORY = { "Trainer", "Trainee", "Courseware", "Organization", "Logistics",
			"Management", "Calendar", "Others" };

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@GetMapping(value = "/new")
	public String viewsFormManagement(Model model) {
		ClassBatchForm classBatchForm = new ClassBatchForm();

		// set History == Fullname
		// Date Created by userFullname
		classBatchForm.setHistory(
				String.valueOf(new Date()) + " - Created by " + (String) httpSession.getAttribute("userFullName"));
		classBatchForm.setAcceptedTraineeNumber(0);
		classBatchForm.setActualTraineeNumber(0);

		model.addAttribute("status", classStatusService.findByClassStatusName("Draft").getClassStatusName());
		model.addAttribute("classBatchForm", classBatchForm);
		model.addAttribute("auditFormOfClassBatch", new AuditFormOfClassBatch());
		model.addAttribute("trainerList", trainerService.findAll());
		model.addAttribute("learningPathList", learningPathService.findAll());
		model.addAttribute("locationList", locationService.findAll());
		model.addAttribute("classAdminList", classAdminService.findAll());
		model.addAttribute("budgetList", budgetService.findAll());
		model.addAttribute("subTypeList", subjectTypeService.findAll());
		model.addAttribute("subSubTypeList", subSubJectTypeService.findAll());
		model.addAttribute("deliveryTypeList", deliveryTypeService.findAll());
		model.addAttribute("formatTypeList", formatTypeService.findAll());
		model.addAttribute("scopeList", scopeService.findAll());
		model.addAttribute("eventCategoryList", EVENT_CATEGORY);
		model.addAttribute("supplierPartnerList", supplierPartnerService.findAll());
		model.addAttribute("relatedPeopleList", trainerService.findAll());

		return "/classManagement/classManagement";
	}

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@PostMapping(value = "/add")
	public String addManagement(@ModelAttribute("classBatchForm") ClassBatchForm classBatchForm,
			@ModelAttribute("auditFormOfClassBatch") AuditFormOfClassBatch auditFormOfClassBatch, Model model,
			@RequestParam("curriculumFile") MultipartFile curriculumFile, RedirectAttributes ra) {

		// set data for audit
		System.err.println(auditFormOfClassBatch.toString());
		Audit audit = new Audit(auditFormOfClassBatch);
		classBatchForm.setAudit(audit);

		// set history
		classBatchForm.setHistory(String.valueOf(new Date()));

		// Check sesson of
		System.out.println("budgetDetails tá»« http Sesson: " + httpSession.getAttribute("budgetDetails"));
		Set<BudgetDetail> budgetDetailsSet = (Set<BudgetDetail>) httpSession.getAttribute("budgetDetails");
		classBatchForm.setSetOfBudgetDetails(budgetDetailsSet);

		// add file and save
		String fileName = StringUtils.cleanPath(curriculumFile.getOriginalFilename());
		classBatchForm.setCurriculum(fileName);
		System.out.println(fileName);

		// ClassCode Format
		// className Format
		classBatchForm.setClassCode(this.formatClassCode(classBatchForm));
		classBatchForm.setClassName(this.formatClassName(classBatchForm));

		// set class status
		classBatchForm.setClassStatus(classStatusService.findByClassStatusName("Draft"));

		// set trainee number
		classBatchForm.setAcceptedTraineeNumber(0);
		classBatchForm.setActualTraineeNumber(0);

		// Set history
		classBatchForm.setHistory(
				String.valueOf(new Date()) + " - Created by " + (String) httpSession.getAttribute("userFullName"));

		// set budgetover (chÃº Ã½ KhÃ´ng tÃ¬m tháº¥y trÆ°á»�ng lÆ°u total vÃ 
		// overbusget

//		set List for select 
		model.addAttribute("trainerList", trainerService.findAll());
		model.addAttribute("locationList", locationService.findAll());
		model.addAttribute("classAdminList", classAdminService.findAll());
		model.addAttribute("learningPathList", learningPathService.findAll());
		model.addAttribute("budgetList", budgetService.findAll());
		model.addAttribute("subTypeList", subjectTypeService.findAll());
		model.addAttribute("subSubTypeList", subSubJectTypeService.findAll());
		model.addAttribute("deliveryTypeList", deliveryTypeService.findAll());
		model.addAttribute("formatTypeList", formatTypeService.findAll());
		model.addAttribute("scopeList", scopeService.findAll());
		model.addAttribute("eventCategoryList", EVENT_CATEGORY);
		model.addAttribute("supplierPartnerList", supplierPartnerService.findAll());
		model.addAttribute("relatedPeopleList", trainerService.findAll());
		model.addAttribute("status", "Draft");
		
		ClassBatch classBatch = new ClassBatch(classBatchForm);
		System.out.println("class Management: " + classBatch.toString());
		classBatchService.save(classBatch);
		
		httpSession.setAttribute("budgetDetails", new HashSet<BudgetDetail>());
		
//		emailService.sendEmailWhenCreate(classBatch, httpServletRequest);
		WebUtils.setRedirectAttributes(ra, null, "Successfully created class.",
				classBatchService.findByClassCode(classBatch.getClassCode()).getId().toString(), "class");
		return "redirect:/classBatch/view";
	}
	
	private void retrieveDataToUpdate(Model model, String classBatchId) {
		httpSession.setAttribute("classBatchIdUpdate", classBatchId);

		System.out.println("classBatchId: " + classBatchId);
		ClassBatch classBatch = classBatchService.findById(Integer.parseInt(classBatchId)).get();
		System.out.println("audit: " + classBatch.getAudit());
		Audit audit;
		AuditFormOfClassBatch auditFormOfClassBatch = null;

		if (classBatch.getAudit() != null) {
			audit = classBatch.getAudit();
			auditFormOfClassBatch = new AuditFormOfClassBatch(audit);
		} else if (classBatch.getAudit() == null) {
			auditFormOfClassBatch = new AuditFormOfClassBatch();
		}

		ClassBatchForm classBatchForm = new ClassBatchForm(classBatch);

//			set List for select 
		model.addAttribute("trainerList", trainerService.findAll());
		model.addAttribute("locationList", locationService.findAll());
		model.addAttribute("classAdminList", classAdminService.findAll());
		model.addAttribute("learningPathList", learningPathService.findAll());
		model.addAttribute("budgetList", budgetService.findAll());
		model.addAttribute("subTypeList", subjectTypeService.findAll());
		model.addAttribute("subSubTypeList", subSubJectTypeService.findAll());
		model.addAttribute("deliveryTypeList", deliveryTypeService.findAll());
		model.addAttribute("formatTypeList", formatTypeService.findAll());
		model.addAttribute("scopeList", scopeService.findAll());
		model.addAttribute("eventCategoryList", EVENT_CATEGORY);
		model.addAttribute("supplierPartnerList", supplierPartnerService.findAll());
		model.addAttribute("relatedPeopleList", trainerService.findAll());

		// send objectform
		model.addAttribute("status", "Draft");
		model.addAttribute("classBatchForm", classBatchForm);
		model.addAttribute("auditFormOfClassBatch", auditFormOfClassBatch);
	}
	
	// redirect data form
		@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
		@RequestMapping(path = { "/update_redirect" }, method = RequestMethod.POST)
		public String postUpdateClassPage(Model model,
				@RequestParam(name = "errorString", required = false) String errorString,
				@RequestParam(name = "selectedClassBatch", required = false) String classBatchId,
				@RequestParam(name = "buttonType", required = false) String buttonType) {

			if (Objects.isNull(classBatchId) || classBatchId.isBlank() || classBatchId.isEmpty()) {
				model.addAttribute("errorString", "Please select a class first.");
				return getClassListingPage(model, "Please select a class first.", Optional.ofNullable(null),
						Optional.ofNullable(null));
			}
			else {
				retrieveDataToUpdate(model, classBatchId);
				return "/classManagement/updatateClassmanagement";
			}
		}
	
	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@RequestMapping(path = { "/update_in_view" }, method = RequestMethod.GET)
	public String getUpdateClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId) {
		System.out.println("classBatchId: " + classBatchId);
		retrieveDataToUpdate(model, classBatchId);
		return "/classManagement/updatateClassmanagement";
	}

	@SuppressWarnings("unchecked")
	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@PostMapping(value = "/update")
	public String update(@ModelAttribute("classBatchForm") ClassBatchForm classBatchForm,
			@ModelAttribute("auditFormOfClassBatch") AuditFormOfClassBatch auditFormOfClassBatch, Model model,
			@RequestParam("curriculumFile") MultipartFile curriculumFile,
			RedirectAttributes ra) {
//		set List for select 
		model.addAttribute("trainerList", trainerService.findAll());
		model.addAttribute("locationList", locationService.findAll());
		model.addAttribute("classAdminList", classAdminService.findAll());
		model.addAttribute("learningPathList", learningPathService.findAll());
		model.addAttribute("budgetList", budgetService.findAll());
		model.addAttribute("subTypeList", subjectTypeService.findAll());
		model.addAttribute("subSubTypeList", subSubJectTypeService.findAll());
		model.addAttribute("deliveryTypeList", deliveryTypeService.findAll());
		model.addAttribute("formatTypeList", formatTypeService.findAll());
		model.addAttribute("scopeList", scopeService.findAll());
		model.addAttribute("eventCategoryList", EVENT_CATEGORY);
		model.addAttribute("supplierPartnerList", supplierPartnerService.findAll());
		model.addAttribute("relatedPeopleList", trainerService.findAll());
		// set id;
		String str = httpSession.getAttribute("classBatchIdUpdate").toString();
		Integer classBatchId = Integer.parseInt(str);
		classBatchForm.setId(classBatchId);
		// set audit form
		Audit audit = new Audit(auditFormOfClassBatch);
		classBatchForm.setAudit(audit);
		// budget detail
		Set<BudgetDetail> setBudgetDetails = (Set<BudgetDetail>) httpSession.getAttribute("budgetDetailsUpdate");
		Set<BudgetDetail> setDetail = new HashSet<BudgetDetail>();
		try {
			
			for (BudgetDetail budgetDetail : setBudgetDetails) {
					BudgetDetail budget = budgetDetailService.save(budgetDetail);
					setDetail.add(budget);
			}
		} catch (NoSuchElementException | NullPointerException  e) {
			System.out.println(e.getMessage());
		}
		System.out.println("update: "+setDetail);
		classBatchForm.setSetOfBudgetDetails(setDetail);
		// set class Status
		ClassStatus classStatus = classStatusService.findByClassStatusName("Draft");
		classBatchForm.setClassStatus(classStatus);

		System.out.println("classbatch: " + classBatchForm);
		System.out.println("Audit: " + auditFormOfClassBatch.toString());
		System.out.println("curriculumFile: " + curriculumFile);

		ClassBatch classBatch = new ClassBatch(classBatchForm);
		classBatch.setId(classBatchId);
		classBatch.setHistory(
				String.valueOf(new Date()) + " - Updated by " + (String) httpSession.getAttribute("userFullName"));

		classBatchService.save(classBatch);
		System.out.println("classbatch: " + classBatch.toString());
		// off session
		httpSession.setAttribute("budgetDetailsUpdate", new HashSet<>());
//		emailService.sendEmailWhenUpdate(classBatch, httpServletRequest);
		WebUtils.setRedirectAttributes(ra, null, "Succesfully updated class.", classBatchId.toString(), "class");
		return "redirect:/classBatch/view";
	}

	@SuppressWarnings("deprecation")
	public String formatClassCode(ClassBatchForm classBatchForm) {
		String site = classBatchForm.getLocation().getAcronym();
		String typeOfClass = classBatchForm.getLearningPath().getTyOfClass();
		String skill = classBatchForm.getLearningPath().getSkill();
		String year = String.valueOf(1900 + new Date().getYear());

		List<ClassBatch> classBatchList = classBatchService.findAll();
		List<ClassBatch> listSkill = new ArrayList<ClassBatch>();
		// thÃªm vÃ o list cÃ¡c skill cÃ¹ng loáº¡i
		String classCode;
		if (classBatchList.size() == 0) {
			classCode = site + "_" + typeOfClass + "_" + skill + "_" + year + "_" + 1;
			return classCode;
		} else {
			for (ClassBatch classBatch : classBatchList) {
				if ((classBatch.getLearningPath().getSkill()).equals(skill)) {
					listSkill.add(classBatch);
				}
			}
		}
		if (listSkill.size() == 0) {
			classCode = site + "_" + typeOfClass + "_" + skill + "_" + year + "_" + 1;
		} else {
			String[] arrClassCode = listSkill.get(listSkill.size() - 1).getClassCode().split("_");
			String serialTemp = arrClassCode[arrClassCode.length - 1];
			int serial = Integer.parseInt(serialTemp) + 1;
			classCode = site + "_" + typeOfClass + "_" + skill + "_" + year + "_" + serial;
		}
		System.out.println("classCode: " + classCode);
		return classCode;
	}

	public String formatClassName(ClassBatchForm classBatchForm) {
		String typeOfClass = "";

		if (Objects.equals(classBatchForm.getLearningPath().getTyOfClass(), "FR")) {
			typeOfClass = "Fresher";
			System.err.println("typeOfClass Fresher: " + typeOfClass);
		} else if (Objects.equals(classBatchForm.getLearningPath().getTyOfClass(), "CP")) {
			typeOfClass = "CampusLink";
			System.err.println("typeOfClass CP: " + typeOfClass);
		}
		String skill = classBatchForm.getLearningPath().getSkill();
		String position = classBatchForm.getLearningPath().getPositionOfSkill();
		String className = typeOfClass + " " + position + " " + skill;
		System.out.println("className: " + className);
		return className;
	}
	
	@GetMapping("/importViews")
	public String getImportTrainee() {
		return "/classManagement/importTrainee";
	}

	@SuppressWarnings({ "unchecked", "deprecation" })
	@PostMapping("/importTranee")
	public String read(@RequestParam("traineeFile") MultipartFile traineeFile, Model model, RedirectAttributes ra) {

		// get session classBatchId from views to importTranee
		int classBatchId = Integer.parseInt(httpSession.getAttribute("classBatchIdFromViewToImportTrainee").toString());
		System.out.println("classBatchID: " + classBatchId);
		if (ReadFileExcell.readFileExcell(traineeFile).get(0) instanceof TraineeForm) {
			List<TraineeForm> list = (List<TraineeForm>) ReadFileExcell.readFileExcell(traineeFile);
			// set University
			// University if == null insert University
			for (TraineeForm traineeForm : list) {
				University university = new University();
				university.setUniversityName(traineeForm.getUniversity());
				Optional<University> uni = Optional
						.ofNullable(universityService.findByUniversityName(traineeForm.getUniversity()));
				try {
					System.out.println("university: " + uni.get());
					if (uni.get() == null) {
						universityService.save(university);
					}
				} catch (NoSuchElementException e) {
					universityService.save(university);
				}
			}
			// set faculy if == null insert facully
			for (TraineeForm traineeForm : list) {
				Faculty faculty = new Faculty();
				faculty.setFacultyName(traineeForm.getFaculty());
				Optional<Faculty> fac = Optional.ofNullable(facultyService.findByFacultyName(traineeForm.getFaculty()));
				try {
					System.out.println("Faculy: " + fac.get());
					if (fac.get() == null) {
						facultyService.save(faculty);
					}
				} catch (NoSuchElementException e) {
					facultyService.save(faculty);
				}
			}
			ClassBatch classBatch = classBatchService.findById(classBatchId).get();
//			Empl_ID	Name	DOB	Gender	University1	Faculty	Phone	Email	Status
			// set data traineeCandidateProfile
			List<Trainee> listOfImportedTrainees = new ArrayList<Trainee>();
			List<TraineeCandidateProfile> listOfImportedTraineeProfiles = new ArrayList<TraineeCandidateProfile>();
			
			for (TraineeForm traineeForm : list) {
				TraineeCandidateProfile traineeCandidateProfile = new TraineeCandidateProfile();
				traineeCandidateProfile.setFullName(traineeForm.getStringName());
				traineeCandidateProfile.setDateOfBirth(new Date(traineeForm.getDOB()));
				traineeCandidateProfile.setGender(traineeForm.getGender());
				
				University university = universityService.findByUniversityName(traineeForm.getUniversity());
				traineeCandidateProfile.setUniversity(university);
				
				Faculty faculty = facultyService.findByFacultyName(traineeForm.getFaculty());
				traineeCandidateProfile.setFaculty(faculty);
				
				traineeCandidateProfile.setPhone(traineeForm.getPhone());
				traineeCandidateProfile.setEmail(traineeForm.getEmail());
				listOfImportedTraineeProfiles.add(traineeCandidateProfile);
				
				Trainee trainee = new Trainee();
				trainee.setClassBatch(classBatch);
				trainee.setStatusInClass(traineeForm.getStatus());
				trainee.setStatus(statusService.findByStatusName("Enrolled"));
				trainee.setTraineeCandidateProfile(traineeCandidateProfile);
				personService.generateAccount(trainee);
				listOfImportedTrainees.add(trainee);
			}
			if (!personService.checkIfPersonInListExisted(listOfImportedTrainees)) {
				traineeCandidateProfileService.saveAll(listOfImportedTraineeProfiles);
				traineeService.saveAll(listOfImportedTrainees);
				classBatch.setAcceptedTraineeNumber(classBatch.getAcceptedTraineeNumber() + listOfImportedTrainees.size());
				classBatchService.save(classBatch);
			} else {
				WebUtils.setRedirectAttributes(ra, "Cannot import trainees to class", null, String.valueOf(classBatchId), "trainee");
				return "redirect:/classBatch/view";
			}
		} else {
			List<String> listError = (List<String>) ReadFileExcell.readFileExcell(traineeFile);
			model.addAttribute("listError", listError);
			System.out.println("else run");
		}
		WebUtils.setRedirectAttributes(ra, "Successfully imported trainees to class.", null, String.valueOf(classBatchId), "trainee");
		return "redirect:/classBatch/view";
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void listClassBatches(Model model, String errorString, Optional<Integer> page, Optional<Integer> size,
			List<ClassBatch> inputListOfClassBatches) {
		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);
		ClassBatchCriteriaModel classBatchCriteriaModel = new ClassBatchCriteriaModel();

		List<Location> listOfLocations = locationService.findAll();
		List<ClassStatus> listOfClassStatuses = classStatusService.findAll();
		List<ClassBatch> listOfClassBatches = new ArrayList<ClassBatch>();
		if (Objects.isNull(inputListOfClassBatches) || inputListOfClassBatches.size() < 1) {
			listOfClassBatches = classBatchService.findAll();
		} else {
			listOfClassBatches = inputListOfClassBatches;
		}
		Set<String> listOfClassNames = classBatchService.findAllClassName();
		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listOfClassBatches,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("classBatchPage", ((List<Page<ClassBatch>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("classBatchErrorString", ((List<String>) paginationAttributes.get("errorString")).get(0));
		model.addAttribute("totalNumberOfClassBatches", listOfClassBatches.size());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("listOfClassBatches", listOfClassBatches);
		model.addAttribute("listOfLocations", listOfLocations);
		model.addAttribute("listOfClassStatuses", listOfClassStatuses);
		model.addAttribute("listOfClassNames", listOfClassNames);
		model.addAttribute("classBatchCriteriaModel", classBatchCriteriaModel);
	}

	@RequestMapping(path = { "/listing" }, method = RequestMethod.GET)
	public String getClassListingPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size) {
		listClassBatches(model, errorString, page, size, null);
		return "/classManagement/class_listing_paged.html";
	}

	@RequestMapping(path = { "/search_result" }, method = RequestMethod.GET)
	public String getSearchResultPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size,
			@SessionAttribute(name = "listOfClassBatches", required = false) List<ClassBatch> listOfClassBatches) {
		listClassBatches(model, errorString, page, size, listOfClassBatches);
		return "/classManagement/class_listing_paged.html";
	}

	private String updateProgression(List<String> requiredStatus, String newStatus, String completeMessage,
			String remarksContent, Model model, String errorString, String classBatchId, String userFullName,
			HttpSession session, RedirectAttributes ra, HttpServletRequest request) {
		String resultString = null;
		if (classBatchService.changeClassProgression(classBatchId, requiredStatus, newStatus, userFullName,
				completeMessage, remarksContent)) {
			switch (newStatus) {
			case ClassBatchProgression.Submit.NEW_STATUS:
				emailService.sendEmailWhenSubmit(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Approve.NEW_STATUS:
				emailService.sendEmailWhenApprove(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Accept.NEW_STATUS:
				emailService.sendEmailWhenAccept(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Start.NEW_STATUS:
				emailService.sendEmailWhenStart(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Finish.NEW_STATUS:
				emailService.sendEmailWhenFinish(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Close.NEW_STATUS:
				emailService.sendEmailWhenClose(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Cancel.NEW_STATUS:
				emailService.sendEmailWhenCancel(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Reject.NEW_STATUS:
				emailService.sendEmailWhenReject(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.Decline.NEW_STATUS:
				emailService.sendEmailWhenDecline(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			case ClassBatchProgression.RequestInfo.NEW_STATUS:
				emailService.sendEmailWhenRequest(classBatchService.findById(Integer.valueOf(classBatchId)).get(),
						request);
				break;
			default:
				break;
			}
			resultString = completeMessage + " successfully.";
		} else {
			resultString = "This class's status must be '" + requiredStatus.toString() + "' to be "
					+ completeMessage.toLowerCase() + ".";
		}
		WebUtils.setRedirectAttributes(ra, errorString, resultString, classBatchId, "class");
		return "redirect:/classBatch/view";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@RequestMapping(path = { "/submit" }, method = RequestMethod.GET)
	public String getSubmitClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Submit.REQUIRED_STATUSES,
				ClassBatchProgression.Submit.NEW_STATUS, ClassBatchProgression.Submit.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/approve" }, method = RequestMethod.GET)
	public String getApproveClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Approve.REQUIRED_STATUSES,
				ClassBatchProgression.Approve.NEW_STATUS, ClassBatchProgression.Approve.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER')")
	@RequestMapping(path = { "/accept" }, method = RequestMethod.GET)
	public String getAcceptClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Accept.REQUIRED_STATUSES,
				ClassBatchProgression.Accept.NEW_STATUS, ClassBatchProgression.Accept.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@RequestMapping(path = { "/start" }, method = RequestMethod.GET)
	public String getStartClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Start.REQUIRED_STATUSES, ClassBatchProgression.Start.NEW_STATUS,
				ClassBatchProgression.Start.COMPLETE_MESSAGE, "", model, errorString, classBatchId, userFullName,
				session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@RequestMapping(path = { "/finish" }, method = RequestMethod.GET)
	public String getFinishClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Finish.REQUIRED_STATUSES,
				ClassBatchProgression.Finish.NEW_STATUS, ClassBatchProgression.Finish.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/close" }, method = RequestMethod.GET)
	public String getCloseClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Close.REQUIRED_STATUSES, ClassBatchProgression.Close.NEW_STATUS,
				ClassBatchProgression.Close.COMPLETE_MESSAGE, "", model, errorString, classBatchId, userFullName,
				session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/cancel" }, method = RequestMethod.GET)
	public String getCancelClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Cancel.REQUIRED_STATUSES,
				ClassBatchProgression.Cancel.NEW_STATUS, ClassBatchProgression.Cancel.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/cancel" }, method = RequestMethod.POST)
	public String postCancelClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "selectedClassBatch", required = false) String classBatchId,
			@RequestParam(name = "buttonType", required = false) String buttonType,
			@SessionAttribute(name = "userFullName", required = false) String userFullName, HttpSession session,
			HttpServletRequest request, RedirectAttributes ra) {

		if (Objects.isNull(classBatchId) || classBatchId.isBlank() || classBatchId.isEmpty()) {
			model.addAttribute("errorString", "Please select a class first.");
			return getClassListingPage(model, "Please select a class first.", Optional.ofNullable(null),
					Optional.ofNullable(null));
		}
		return updateProgression(ClassBatchProgression.Cancel.REQUIRED_STATUSES,
				ClassBatchProgression.Cancel.NEW_STATUS, ClassBatchProgression.Cancel.COMPLETE_MESSAGE, "", model,
				errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/reject" }, method = RequestMethod.POST)
	public String postRejectClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "rejectClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Reject.REQUIRED_STATUSES,
				ClassBatchProgression.Reject.NEW_STATUS, ClassBatchProgression.Reject.COMPLETE_MESSAGE, inputRemarks,
				model, errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER')")
	@RequestMapping(path = { "/decline" }, method = RequestMethod.POST)
	public String postDeclineClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "declineClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.Decline.REQUIRED_STATUSES,
				ClassBatchProgression.Decline.NEW_STATUS, ClassBatchProgression.Decline.COMPLETE_MESSAGE, inputRemarks,
				model, errorString, classBatchId, userFullName, session, ra, request);
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/requestInfo" }, method = RequestMethod.POST)
	public String postRequestInfoClassPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "requestInfoClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			@RequestParam(name = "inputRemarks", required = false) String inputRemarks, HttpSession session,
			RedirectAttributes ra, HttpServletRequest request) {
		return updateProgression(ClassBatchProgression.RequestInfo.REQUIRED_STATUSES,
				ClassBatchProgression.RequestInfo.NEW_STATUS, ClassBatchProgression.RequestInfo.COMPLETE_MESSAGE,
				inputRemarks, model, errorString, classBatchId, userFullName, session, ra, request);

	}

	@RequestMapping(path = { "/search_result" }, method = RequestMethod.POST)
	public String postSearchResultPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size,
			ClassBatchCriteriaModel classBatchCriteriaModel, HttpSession session) {

		List<ClassBatch> listOfClassBatches = classBatchService.filterClassSearchCriteria(classBatchCriteriaModel);
		session.setAttribute("listOfClassBatches", listOfClassBatches);
		return getSearchResultPage(model, errorString, page, size, listOfClassBatches);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(path = { "/view" }, method = RequestMethod.GET)
	public String getViewClassPage(Model model, @ModelAttribute(name = "errorString") String errorString,
			@ModelAttribute(name = "resultString") String resultString,
			@ModelAttribute(name = "classBatchId") String classBatchId,
			@ModelAttribute(name = "selectedTab") String selectedTab,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size, HttpSession session) {

		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);

		System.out.println("view ClassBatchId: " + classBatchId);
		// set session classBatch id
		httpSession.setAttribute("classBatchIdFromViewToImportTrainee", classBatchId);

		ClassBatch classBatch = classBatchService.findById(Integer.valueOf(classBatchId)).get();
		System.out.println(classBatch.toString());
		ClassBatchViewModel classBatchViewModel = new ClassBatchViewModel(classBatch);

		List<Trainee> listOfTrainee = new ArrayList<Trainee>(classBatch.getSetOfTrainees());
		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listOfTrainee,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("traineePage", ((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("traineeErrorString", ((List<String>) paginationAttributes.get("errorString")).get(0));
		model.addAttribute("totalNumberOfTrainees", listOfTrainee.size());
		model.addAttribute("traineeCriteriaModel", new TraineeCriteriaModel());
		session.setAttribute("currentViewClassBatchId", classBatchId);
		model.addAttribute("classBatchViewModel", classBatchViewModel);

		TraineeToChangeStatus listOfTraineeToChangeStatus = new TraineeToChangeStatus(
				((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0).getContent());
		model.addAttribute("traineeToChangeStatus", listOfTraineeToChangeStatus);
		if (Objects.isNull(selectedTab) || selectedTab.isEmpty() || selectedTab.isBlank()) {
			selectedTab = "class";
		}
		model.addAttribute("errorString", errorString);
		model.addAttribute("resultString", resultString);
		model.addAttribute("selectedTab", selectedTab);

		return "/classManagement/class_view_paged";
	}

}
package com.example.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.example.demo.entity.Budget;
import com.example.demo.entity.Candidate;
import com.example.demo.entity.Channel;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.ClassAdminProfile;
import com.example.demo.entity.ClassStatus;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.DeliveryType;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.FormatType;
import com.example.demo.entity.LearningPath;
import com.example.demo.entity.Location;
import com.example.demo.entity.Role;
import com.example.demo.entity.Scope;
import com.example.demo.entity.Status;
import com.example.demo.entity.SubSubjectType;
import com.example.demo.entity.SubjectType;
import com.example.demo.entity.SupplierPartner;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.TraineeCandidateProfile;
import com.example.demo.entity.Trainer;
import com.example.demo.entity.TrainerProfile;
import com.example.demo.entity.University;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.SupplierPartnerRepository;
import com.example.demo.service.UserService;
import com.example.demo.service.iservice.IBudgetService;
import com.example.demo.service.iservice.ICandidateService;
import com.example.demo.service.iservice.IChannelService;
import com.example.demo.service.iservice.IClassAdminProfileService;
import com.example.demo.service.iservice.IClassAdminService;
import com.example.demo.service.iservice.IClassBatchService;
import com.example.demo.service.iservice.IClassStatusService;
import com.example.demo.service.iservice.IDeliveryTypeService;
import com.example.demo.service.iservice.IFacultyService;
import com.example.demo.service.iservice.IFormatTypeService;
import com.example.demo.service.iservice.ILearningPathService;
import com.example.demo.service.iservice.ILocationService;
import com.example.demo.service.iservice.IPersonService;
import com.example.demo.service.iservice.IRoleService;
import com.example.demo.service.iservice.IScopeService;
import com.example.demo.service.iservice.ISubSubJectTypeService;
import com.example.demo.service.iservice.ISubjectTypeService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.service.iservice.ITrainerProfileService;
import com.example.demo.service.iservice.ITrainerService;
import com.example.demo.service.iservice.IUniversityService;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.HibernateValidator;

@SpringBootApplication
@ComponentScan(value = "com.example.demo")
public class MockProjectApplication implements CommandLineRunner {

	@Autowired
	IRoleService roleService;
	
	@Autowired
	IChannelService channelService;

	@Autowired
	ITrainerService trainerService;

	@Autowired
	ITrainerProfileService trainerProfileService;

	@Autowired
	IClassAdminService classAdminService;

	@Autowired
	IClassAdminProfileService classAdminProfileService;

	@Autowired
	IBudgetService budgetService;

	@Autowired
	IClassStatusService classStatusService;

	@Autowired
	ISubjectTypeService subjectTypeService;

	@Autowired
	ISubSubJectTypeService subSubjectTypeservice;

	@Autowired
	IDeliveryTypeService deliveryTypeService;

	@Autowired
	IFormatTypeService formatTypeService;

	@Autowired
	IScopeService scopeService;

	@Autowired
	StatusRepository statusService;

	@Autowired
	ILocationService locationService;

	@Autowired
	IClassBatchService classBatchService;

	@Autowired
	ITraineeService traineeService;

	@Autowired
	IUniversityService universityService;

	@Autowired
	IFacultyService facultyService;

	@Autowired
	ICandidateService candidateService;

	@Autowired
	ILearningPathService learningPathService;

	@Autowired
	SupplierPartnerRepository supplierPartnerService;

	@Autowired
	UserService userService;
	
	@Autowired
	IPersonService personService;

	public static void main(String[] args) {
		SpringApplication.run(MockProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		// Tao role
//		Role userRole = new Role();
//		userRole.setName("ROLE_USER");
		Role adminRole = new Role();
		adminRole.setName("ROLE_ADMIN");
		Role trainerRole = new Role();
		trainerRole.setName("ROLE_TRAINER");
		Role faManagerRole = new Role();
		faManagerRole.setName("ROLE_FA_MANAGER");
		Role faRecRole = new Role();
		faRecRole.setName("ROLE_FA_REC");
		Role deliveryManagerRole = new Role();
		deliveryManagerRole.setName("ROLE_DELIVERY_MANAGER");

		// Dua role vao database
//		System.out.println("Saved role: " + roleRepository.save(userRole).getName());
		System.out.println("Saved role: " + roleService.save(adminRole).getName());
		System.out.println("Saved role: " + roleService.save(trainerRole).getName());
		System.out.println("Saved role: " + roleService.save(faManagerRole).getName());
		System.out.println("Saved role: " + roleService.save(faRecRole).getName());
		System.out.println("Saved role: " + roleService.save(deliveryManagerRole).getName());

//		add Learning Path 
		LearningPath learningPath = new LearningPath();
		learningPath.setDetailedInformation("Java_Fresher");
		learningPath.setLearningPathFileAddress("LMS.POLY.VN");
		learningPath.setLearningPathName("Java_Fresher");
		learningPath.setPositionOfSkill("Developer");
		learningPath.setSkill("Java");
		learningPath.setTyOfClass("FR");

		LearningPath learningPath1 = new LearningPath();
		learningPath1.setDetailedInformation(".Net_Fresher");
		learningPath1.setLearningPathFileAddress("LMS.POLY.VN");
		learningPath1.setLearningPathName(".Net_Fresher");
		learningPath1.setPositionOfSkill("Developer");
		learningPath1.setSkill(".Net");
		learningPath1.setTyOfClass("FR");

		LearningPath learningPath2 = new LearningPath();
		learningPath2.setDetailedInformation("Test_Fresher");
		learningPath2.setLearningPathFileAddress("LMS.POLY.VN");
		learningPath2.setLearningPathName("Test_Fresher");
		learningPath2.setPositionOfSkill("Tester");
		learningPath2.setSkill("Test");
		learningPath2.setTyOfClass("CP");

		LearningPath learningPath3 = new LearningPath();
		learningPath3.setDetailedInformation("SQL_Fresher");
		learningPath3.setLearningPathFileAddress("LMS.POLY.VN");
		learningPath3.setLearningPathName("SQL_Fresher");
		learningPath3.setPositionOfSkill("Developer");
		learningPath3.setSkill("SQL");
		learningPath3.setTyOfClass("FR");

		LearningPath learningPath4 = new LearningPath();
		learningPath4.setDetailedInformation("Python_Fresher");
		learningPath4.setLearningPathFileAddress("LMS.POLY.VN");
		learningPath4.setLearningPathName("Python_Fresher");
		learningPath4.setPositionOfSkill("Developer");
		learningPath4.setSkill("Python");
		learningPath4.setTyOfClass("FR");

		learningPathService.save(learningPath);
		learningPathService.save(learningPath1);
		learningPathService.save(learningPath2);
		learningPathService.save(learningPath3);
		learningPathService.save(learningPath4);

		// Supplier/ Partner
		SupplierPartner supplierPartner = new SupplierPartner();
		supplierPartner.setSupplierPartnerName("Fsoft");
		supplierPartner.setRemarks("no");

		SupplierPartner supplierPartner1 = new SupplierPartner();
		supplierPartner1.setSupplierPartnerName("FPL");
		supplierPartner1.setRemarks("no");

		SupplierPartner supplierPartner2 = new SupplierPartner();
		supplierPartner2.setSupplierPartnerName("FU");
		supplierPartner2.setRemarks("no");

		supplierPartnerService.save(supplierPartner);
		supplierPartnerService.save(supplierPartner1);
		supplierPartnerService.save(supplierPartner2);

		// Tao trainer
		Trainer trainer = new Trainer();
		trainer.setUsername("trainer");
		trainer.setAccount("KhoaNM");

		List<Role> listOfRole2 = new ArrayList<Role>();
		listOfRole2.add(roleService.findByName("ROLE_TRAINER"));

		TrainerProfile trainerProfile = new TrainerProfile();
		trainerProfile.setTrainer(trainer);
		trainerProfile.setFullName("Nguyen Minh Khoa");
		trainerProfile.setDateOfBirth(DateUtils.parseDateFromString("1998-08-09"));
		trainerProfile.setGender("Male");
		trainerProfile.setUnit(1);
		trainerProfile.setMajor("Mechatronic");
		trainerProfile.setPhone("0836255955");
		trainerProfile.setEmail("khoa@gmail.com");
		trainerProfile.setExperience(3);
		trainerProfile.setRemarks("demo");

		trainer.setTrainerProfile(trainerProfile);

		userService.save(trainer, "123", listOfRole2);
		System.out.println("created trainer: " + trainer.toString());

		trainerProfileService.save(trainerProfile);

		//
		Trainer trainer2 = new Trainer();
		trainer2.setUsername("trainer2");

		List<Role> listOfRoles5 = new ArrayList<Role>();
		listOfRoles5.add(roleService.findByName("ROLE_TRAINER"));

		TrainerProfile trainerProfile2 = new TrainerProfile();
		trainerProfile2.setTrainer(trainer2);
		trainerProfile2.setFullName("Nguyen Van Huan Luyen");
		trainerProfile2.setDateOfBirth(DateUtils.parseDateFromString("1998-08-09"));
		trainerProfile2.setGender("Male");
		trainerProfile2.setUnit(1);
		trainerProfile2.setMajor("Computer Science");
		trainerProfile2.setPhone("0836254955");
		trainerProfile2.setEmail("trainer02@gmail.com");
		trainerProfile2.setExperience(3);
		trainerProfile2.setRemarks("demo");
		trainer2.setTrainerProfile(trainerProfile2);
		personService.generateAccount(trainer2);
		userService.save(trainer2, "123", listOfRoles5);
		System.out.println("created trainer: " + trainer2.toString());

		trainerProfileService.save(trainerProfile2);
		//
		Trainer trainer3 = new Trainer();
		trainer3.setUsername("trainer3");

		List<Role> listOfRoles6 = new ArrayList<Role>();
		listOfRoles6.add(roleService.findByName("ROLE_TRAINER"));

		TrainerProfile trainerProfile3 = new TrainerProfile();
		trainerProfile3.setTrainer(trainer3);
		trainerProfile3.setFullName("Le Thi Hang");
		trainerProfile3.setDateOfBirth(DateUtils.parseDateFromString("1998-08-09"));
		trainerProfile3.setGender("Female");
		trainerProfile3.setUnit(1);
		trainerProfile3.setMajor("Computer Science");
		trainerProfile3.setPhone("0832255955");
		trainerProfile3.setEmail("trainer03@gmail.com");
		trainerProfile3.setExperience(3);
		trainerProfile3.setRemarks("demo");
		trainer3.setTrainerProfile(trainerProfile3);
		personService.generateAccount(trainer3);
		userService.save(trainer3, "123", listOfRoles6);
		System.out.println("created trainer: " + trainer3.toString());

		trainerProfileService.save(trainerProfile3);

		// Tao admin
		ClassAdmin admin = new ClassAdmin();
		admin.setUsername("admin");
		List<Role> listOfRole = Arrays.asList(roleService.findByName("ROLE_ADMIN"));
		ClassAdminProfile classAdminProfile = new ClassAdminProfile();
		classAdminProfile.setClassAdmin(admin);
		classAdminProfile.setFullName("Le Thi Anh");
		classAdminProfile.setDateOfBirth(DateUtils.parseDateFromString("2000-12-13"));
		classAdminProfile.setGender("Female");
		classAdminProfile.setPhone("0123423732");
		classAdminProfile.setEmail("adminA@gmail.com");
		classAdminProfile.setRemarks("demo");
		admin.setClassAdminProfile(classAdminProfile);
		personService.generateAccount(admin);
		userService.save(admin, "123", listOfRole);
		classAdminProfileService.save(classAdminProfile);

		//
		ClassAdmin admin2 = new ClassAdmin();
		admin2.setUsername("admin2");
		List<Role> listOfRoles7 = Arrays.asList(roleService.findByName("ROLE_ADMIN"));
		ClassAdminProfile classAdminProfile2 = new ClassAdminProfile();
		classAdminProfile2.setClassAdmin(admin2);
		classAdminProfile2.setFullName("Tran Van Ad Min");
		classAdminProfile2.setDateOfBirth(DateUtils.parseDateFromString("2000-12-13"));
		classAdminProfile2.setGender("Male");
		classAdminProfile2.setPhone("0123422732");
		classAdminProfile2.setEmail("admin2@gmail.com");
		classAdminProfile2.setRemarks("demo");
		admin2.setClassAdminProfile(classAdminProfile2);
		personService.generateAccount(admin2);
		userService.save(admin2, "123", listOfRoles7);
		classAdminProfileService.save(classAdminProfile2);

		// Tao FA manager
		FAManager faManager = new FAManager();
		faManager.setUsername("famanager");
		faManager.setFullName("Ma Na Gio");
		faManager.setAccount("GioMN");
		faManager.setEmail("nguyenminhkhoa.nmk98@gmail.com");

		List<Role> listOfRole3 = new ArrayList<Role>();
//		listOfRole3.add(roleRepository.findByName("ROLE_USER"));
		listOfRole3.add(roleService.findByName("ROLE_FA_MANAGER"));

		HibernateValidator.validateEntityAndGetMessage(faManager);

		userService.save(faManager, "123", listOfRole3);
		System.out.println("created Fa Manager: " + faManager.toString());

		// Tao FA Rec
		FARec faRec = new FARec();
		faRec.setUsername("farec");
		faRec.setFullName("Ep A Rec");
		faRec.setAccount("RecEA");
		faRec.setEmail("farec@gmail.com");

		List<Role> listOfRole4 = new ArrayList<Role>();
//		listOfRole4.add(roleRepository.findByName("ROLE_USER"));
		listOfRole4.add(roleService.findByName("ROLE_FA_REC"));

		HibernateValidator.validateEntityAndGetMessage(faRec);

		userService.save(faRec, "123", listOfRole4);
		System.out.println("created Fa Rec: " + faRec.toString());

		// Tao Delivery Manager
		DeliveryManager deliManager = new DeliveryManager();
		deliManager.setUsername("delimanager");
		deliManager.setFullName("De Li Ve Ry");
		deliManager.setAccount("RyDLV");
		deliManager.setEmail("delimanager@gmail.com");

		List<Role> listOfRole5 = new ArrayList<Role>();
//		listOfRole5.add(roleRepository.findByName("ROLE_USER"));
		listOfRole5.add(roleService.findByName("ROLE_DELIVERY_MANAGER"));

		HibernateValidator.validateEntityAndGetMessage(deliManager);

		userService.save(deliManager, "123", listOfRole5);
		System.out.println("created Delivery manager: " + deliManager.toString());

		// Tao cac entity lien quan den ClassBatch
		budgetService.save(new Budget("CTC_Project_ADP"));
		budgetService.save(new Budget("CTC_Fresher_Allowance"));
		budgetService.save(new Budget("CTC_Fresher_Training"));
		budgetService.save(new Budget("CTC_Specific_Fresher_Allowance"));
		budgetService.save(new Budget("CTC_Specific_Fresher_Training"));
		budgetService.save(new Budget("CTC_Specific_Fresher_Training_Award"));
		budgetService.save(new Budget("CTC_FU"));
		budgetService.save(new Budget("CTC_Uni"));

		classStatusService.save(new ClassStatus("Planned"));
		classStatusService.save(new ClassStatus("Planning"));
		classStatusService.save(new ClassStatus("In-progress"));
		classStatusService.save(new ClassStatus("Pending for review"));
		classStatusService.save(new ClassStatus("Draft"));
		classStatusService.save(new ClassStatus("Submitted"));
		classStatusService.save(new ClassStatus("Closed"));
		classStatusService.save(new ClassStatus("Declined"));
		classStatusService.save(new ClassStatus("Waiting for more information"));
		classStatusService.save(new ClassStatus("Rejected"));
		classStatusService.save(new ClassStatus("Cancelled"));

		subjectTypeService.save(new SubjectType("Organization Overview & Culture"));
		subjectTypeService.save(new SubjectType("Company Process"));
		subjectTypeService.save(new SubjectType("Standard Process"));
		subjectTypeService.save(new SubjectType("IT Technical"));
		subjectTypeService.save(new SubjectType("Non-IT Technical"));
		subjectTypeService.save(new SubjectType("Foreign Language"));
		subjectTypeService.save(new SubjectType("Soft Skill"));
		subjectTypeService.save(new SubjectType("Management"));

		subSubjectTypeservice.save(new SubSubjectType("Cloud"));
		subSubjectTypeservice.save(new SubSubjectType("Big Data"));
		subSubjectTypeservice.save(new SubSubjectType("CAD"));
		subSubjectTypeservice.save(new SubSubjectType("CAE"));
		subSubjectTypeservice.save(new SubSubjectType("SAP"));
		subSubjectTypeservice.save(new SubSubjectType("IT General"));
		subSubjectTypeservice.save(new SubSubjectType("Test"));
		subSubjectTypeservice.save(new SubSubjectType("Others"));

		deliveryTypeService.save(new DeliveryType("Class"));
		deliveryTypeService.save(new DeliveryType("Seminar"));
		deliveryTypeService.save(new DeliveryType("Exam"));
		deliveryTypeService.save(new DeliveryType("Contest"));
		deliveryTypeService.save(new DeliveryType("Certificate"));
		deliveryTypeService.save(new DeliveryType("Club"));
		deliveryTypeService.save(new DeliveryType("OJT"));
		deliveryTypeService.save(new DeliveryType("Others"));

		formatTypeService.save(new FormatType("Online"));
		formatTypeService.save(new FormatType("Offline"));
		formatTypeService.save(new FormatType("Blended"));

		scopeService.save(new Scope("Company"));
		scopeService.save(new Scope("Unit"));
		scopeService.save(new Scope("Outside"));

		statusService.save(new Status("Waiting for Class"));
		statusService.save(new Status("Waiting for Allocation"));
		statusService.save(new Status("In-progress"));
		statusService.save(new Status("Allocated"));
		statusService.save(new Status("Enrolled"));
		statusService.save(new Status("Drop-out"));
		statusService.save(new Status("Deferred"));
		statusService.save(new Status("New"));
		statusService.save(new Status("Test - Pass"));
		statusService.save(new Status("Test - Fail"));
		statusService.save(new Status("Interview - Pass"));
		statusService.save(new Status("InterView - Fail"));

		locationService.save(new Location("District 1", "D1"));
		locationService.save(new Location("District 2", "D2"));
		locationService.save(new Location("District 3", "D3"));
		locationService.save(new Location("District 4", "D4"));
		locationService.save(new Location("District 5", "D5"));
		locationService.save(new Location("District 6", "D6"));
		locationService.save(new Location("District 7", "D7"));
		locationService.save(new Location("District 8", "D8"));
		locationService.save(new Location("District 9", "D9"));
		locationService.save(new Location("District 10", "D10"));
		locationService.save(new Location("District 11", "D11"));
		locationService.save(new Location("District 12", "D12"));
		locationService.save(new Location("Thu Duc", "TD"));
		locationService.save(new Location("Cu Chi", "CC"));
		locationService.save(new Location("Can Gio", "CG"));
		locationService.save(new Location("Ha Noi", "HN"));
		locationService.save(new Location("Da Nang", "DN"));
		locationService.save(new Location("Hai Phong", "HP"));
		locationService.save(new Location("Can Tho", "CT"));

		universityService.save(new University("Dai Hoc FPT"));
		universityService.save(new University("Dai Hoc Bach Khoa"));

		facultyService.save(new Faculty("Mechatronics"));
		facultyService.save(new Faculty("Information Technology"));
		facultyService.save(new Faculty("Computer Science"));

//		Set<Trainer> setOfTrainers = new HashSet<Trainer>();
//		setOfTrainers.add(trainerService.findByUsername("trainer"));
//
//		ClassBatch c1 = new ClassBatch();
//		c1.setClassName("Fresher Developer Java");
//		c1.setClassCode("HCM_FR_Java_18_01");
//		c1.setPlannedTraineeNumber(20);
//		c1.setAcceptedTraineeNumber(1);
//		c1.setActualTraineeNumber(0);
//		c1.setBudget(budgetService.findByBudgetName("CTC_Specific_Fresher_Training_Award"));
//		c1.setClassAdmin(classAdminService.findByUsername("admin"));
//		c1.setExpectedStartDate(DateUtils.parseDateFromString("2022-03-03"));
//		c1.setExpectedEndDate(DateUtils.parseDateFromString("2023-04-5"));
//		c1.setLocation(locationService.findByLocationName("District 1"));
//		c1.setClassStatus(classStatusService.findByClassStatusName("Draft"));
//		c1.setSetOfTrainers(setOfTrainers);
//		Set<Trainee> setOfTrainees = new HashSet<Trainee>();
//		c1.setSetOfTrainees(setOfTrainees);
//		c1.setMasterTrainer(trainerService.findByUsername("trainer"));
//
//		LearningPath learningPathTemp = learningPathService.findById(1).get();
//		c1.setLearningPath(learningPathTemp);
//		;
//		System.out.println("setOf trainers: " + c1.getSetOfTrainers().toString());
//
//		ClassBatch c2 = new ClassBatch();
//		c2.setClassName("Fresher Developer C#");
//		c2.setClassCode("HCM_FR_C#_04_03");
//		c2.setActualStartDate(DateUtils.parseDateFromString("2022-04-03"));
//		c2.setActualEndDate(DateUtils.parseDateFromString("2023-04-01"));
//		c2.setLocation(locationService.findByLocationName("District 5"));
//		c2.setClassStatus(classStatusService.findByClassStatusName("In-progress"));
//
//		LearningPath learningPathTemp2 = learningPathService.findById(2).get();
//		c2.setLearningPath(learningPathTemp2);
//
//		ClassBatch c3 = new ClassBatch();
//		c3.setClassName("Fresher Developer HTML");
//		c3.setClassCode("HCM_FR_HTML_09_03");
//		c3.setActualStartDate(DateUtils.parseDateFromString("2022-09-03"));
//		c3.setActualEndDate(DateUtils.parseDateFromString("2022-09-01"));
//		c3.setLocation(locationService.findByLocationName("Thu Duc"));
//		c3.setClassStatus(classStatusService.findByClassStatusName("Draft"));
//		LearningPath learningPathTemp3 = learningPathService.findById(2).get();
//		c3.setLearningPath(learningPathTemp3);
//
//		ClassBatch c4 = new ClassBatch();
//		c4.setClassName("Fresher Developer C");
//		c4.setClassCode("HCM_FR_C_11_03");
//		c4.setActualStartDate(DateUtils.parseDateFromString("2022-11-03"));
//		c4.setActualEndDate(DateUtils.parseDateFromString("2022-08-05"));
//		c4.setLocation(locationService.findByLocationName("Cu Chi"));
//		c4.setClassStatus(classStatusService.findByClassStatusName("Draft"));
//		LearningPath learningPathTemp4 = learningPathService.findById(3).get();
//		c4.setLearningPath(learningPathTemp4);
//
//		ClassBatch c5 = new ClassBatch();
//		c5.setClassName("Fresher Developer SQL");
//		c5.setClassCode("HCM_FR_SQL_15_04");
//		c5.setActualStartDate(DateUtils.parseDateFromString("2022-11-04"));
//		c5.setActualEndDate(DateUtils.parseDateFromString("2022-08-05"));
//		c5.setLocation(locationService.findByLocationName("District 3"));
//		c5.setClassStatus(classStatusService.findByClassStatusName("Rejected"));
//		LearningPath learningPathTemp5 = learningPathService.findById(1).get();
//		c5.setLearningPath(learningPathTemp5);
//
//		classBatchService.save(c1);
//		classBatchService.save(c2);
//		classBatchService.save(c3);
//		classBatchService.save(c4);
//		classBatchService.save(c5);
//		for (ClassBatch clazz : classBatchService.findAll()) {
//			System.out.println(clazz.getClassCode());
//		}

		Trainee trainee1 = new Trainee();
		TraineeCandidateProfile tcp1 = new TraineeCandidateProfile();
		tcp1.setFullName("Nguyen Thi Train Nee");
		tcp1.setGender("Female");
		tcp1.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("21/12/1998"));
		tcp1.setPhone("01234765876");
		tcp1.setEmail("example@gmail.com");
		tcp1.setUniversity(universityService.findByUniversityName("Dai Hoc FPT"));
		tcp1.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		tcp1.setAllocationStatus("Not allocated");
		trainee1.setTraineeCandidateProfile(tcp1);
		trainee1.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee1);
		traineeService.save(trainee1);

		Trainee trainee2 = new Trainee();
		TraineeCandidateProfile tcp2 = new TraineeCandidateProfile();
		tcp2.setFullName("Le Van Vi Du");
		tcp2.setGender("Male");
		tcp2.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("01/01/2001"));
		tcp2.setPhone("0919874536");
		tcp2.setEmail("trainee2@example.com");
		tcp2.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		tcp2.setFaculty(facultyService.findByFacultyName("Computer Science"));
		tcp2.setAllocationStatus("Not allocated");
		trainee2.setTraineeCandidateProfile(tcp2);
		trainee2.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee2);
		traineeService.save(trainee2);

		Trainee trainee3 = new Trainee();
		TraineeCandidateProfile tcp3 = new TraineeCandidateProfile();
		tcp3.setFullName("Ho Ngoc Ha");
		tcp3.setGender("Female");
		tcp3.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("01/01/2001"));
		tcp3.setPhone("0346284526");
		tcp3.setEmail("ngocha@example.com");
		tcp3.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		tcp3.setFaculty(facultyService.findByFacultyName("Information Technology"));
		tcp3.setAllocationStatus("Not allocated");
		trainee3.setTraineeCandidateProfile(tcp3);
		trainee3.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee3);
		traineeService.save(trainee3);

		Trainee trainee4 = new Trainee();
		TraineeCandidateProfile tcp4 = new TraineeCandidateProfile();
		tcp4.setFullName("Dam Vinh Hung");
		tcp4.setGender("Male");
		tcp4.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("01/01/2001"));
		tcp4.setPhone("0346782526");
		tcp4.setEmail("mrdam@example.com");
		tcp4.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		tcp4.setFaculty(facultyService.findByFacultyName("Information Technology"));
		tcp4.setAllocationStatus("Not allocated");
		trainee4.setTraineeCandidateProfile(tcp4);
		trainee4.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee4);
		traineeService.save(trainee4);

		Trainee trainee5 = new Trainee();
		TraineeCandidateProfile tcp5 = new TraineeCandidateProfile();
		tcp5.setFullName("Nguyen Binh An");
		tcp5.setGender("Male");
		tcp5.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("01/01/2001"));
		tcp5.setPhone("0346242526");
		tcp5.setEmail("anbinh@example.com");
		tcp5.setUniversity(universityService.findByUniversityName("Dai Hoc FPT"));
		tcp5.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		tcp5.setAllocationStatus("Not allocated");
		trainee5.setTraineeCandidateProfile(tcp5);
		trainee5.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee5);
		traineeService.save(trainee5);

		Trainee trainee6 = new Trainee();
		TraineeCandidateProfile tcp6 = new TraineeCandidateProfile();
		tcp6.setFullName("Nguyen Thi Bich");
		tcp6.setGender("Female");
		tcp6.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("11/12/1998"));
		tcp6.setPhone("0346243526");
		tcp6.setEmail("bichnguyen@example.com");
		tcp6.setUniversity(universityService.findByUniversityName("Dai Hoc FPT"));
		tcp6.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		tcp6.setAllocationStatus("Not allocated");
		trainee6.setTraineeCandidateProfile(tcp6);
		trainee6.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee6);
		traineeService.save(trainee6);

		Trainee trainee7 = new Trainee();
		TraineeCandidateProfile tcp7 = new TraineeCandidateProfile();
		tcp7.setFullName("Le Thi Chau");
		tcp7.setGender("Female");
		tcp7.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString("11/12/1998"));
		tcp7.setPhone("0866257526");
		tcp7.setEmail("chaule@example.com");
		tcp7.setUniversity(universityService.findByUniversityName("Dai Hoc FPT"));
		tcp7.setFaculty(facultyService.findByFacultyName("Information Technology"));
		tcp7.setAllocationStatus("Not allocated");
		trainee7.setTraineeCandidateProfile(tcp7);
		trainee7.setStatus(statusService.findByStatusName("Waiting for Class"));
		personService.generateAccount(trainee7);
		traineeService.save(trainee7);

		TraineeCandidateProfile traineeCandidateProfile1 = new TraineeCandidateProfile();
		traineeCandidateProfile1.setFullName("Le Thi Luu");
		traineeCandidateProfile1.setDateOfBirth(DateUtils.parseDateFromString("2000-08-08"));
		traineeCandidateProfile1.setGender("Female");
		traineeCandidateProfile1.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		traineeCandidateProfile1.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		traineeCandidateProfile1.setPhone("0123456789");
		traineeCandidateProfile1.setEmail("lethiluu@gmail.com");

		TraineeCandidateProfile traineeCandidateProfile2 = new TraineeCandidateProfile();
		traineeCandidateProfile2.setFullName("Le Thi Dao");
		traineeCandidateProfile2.setDateOfBirth(DateUtils.parseDateFromString("2003-08-08"));
		traineeCandidateProfile2.setGender("Female");
		traineeCandidateProfile2.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		traineeCandidateProfile2.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		traineeCandidateProfile2.setPhone("0123456788");
		traineeCandidateProfile2.setEmail("lethidao@gmail.com");

		TraineeCandidateProfile traineeCandidateProfile3 = new TraineeCandidateProfile();
		traineeCandidateProfile3.setFullName("Le Van Tham");
		traineeCandidateProfile3.setDateOfBirth(DateUtils.parseDateFromString("1996-08-08"));
		traineeCandidateProfile3.setGender("Male");
		traineeCandidateProfile3.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		traineeCandidateProfile3.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		traineeCandidateProfile3.setPhone("0123456787");
		traineeCandidateProfile3.setEmail("levantham@gmail.com");

		TraineeCandidateProfile traineeCandidateProfile4 = new TraineeCandidateProfile();
		traineeCandidateProfile4.setFullName("Le Van Luu");
		traineeCandidateProfile4.setDateOfBirth(DateUtils.parseDateFromString("1988-08-08"));
		traineeCandidateProfile4.setGender("Male");
		traineeCandidateProfile4.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		traineeCandidateProfile4.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		traineeCandidateProfile4.setPhone("0123456786");
		traineeCandidateProfile4.setEmail("levanluu@gmail.com");

		TraineeCandidateProfile traineeCandidateProfile5 = new TraineeCandidateProfile();
		traineeCandidateProfile5.setFullName("Le Van Lan");
		traineeCandidateProfile5.setDateOfBirth(DateUtils.parseDateFromString("1996-08-08"));
		traineeCandidateProfile5.setGender("Male");
		traineeCandidateProfile5.setUniversity(universityService.findByUniversityName("Dai Hoc Bach Khoa"));
		traineeCandidateProfile5.setFaculty(facultyService.findByFacultyName("Mechatronics"));
		traineeCandidateProfile5.setPhone("0123456785");
		traineeCandidateProfile5.setEmail("levanlan@gmail.com");
		
		// Create Channel
		Channel channel = new Channel("samsung");
		channelService.save(channel);

		// Create Candidate
		Candidate candidate1 = new Candidate();
		candidate1.setTraineeCandidateProfile(traineeCandidateProfile1);
		candidate1.setAccount("abc");
		candidate1.setChannel(channelService.findByChannelName("samsung"));
		candidate1.setLocation(locationService.findByLocationName("Ha Noi"));
		candidate1.setStatus(statusService.findByStatusName("New"));
		candidate1
				.setAccount(personService.generateAccountFromName(candidate1.getTraineeCandidateProfile().getFullName()));

		Candidate candidate2 = new Candidate();
		candidate2.setTraineeCandidateProfile(traineeCandidateProfile2);
		candidate2.setAccount("aabc");
		candidate2.setChannel(channelService.findByChannelName("samsung"));
		candidate2.setLocation(locationService.findByLocationName("Ha Noi"));
		candidate2.setStatus(statusService.findByStatusName("Test - Pass"));
		candidate2
				.setAccount(personService.generateAccountFromName(candidate2.getTraineeCandidateProfile().getFullName()));

		Candidate candidate3 = new Candidate();
		candidate3.setTraineeCandidateProfile(traineeCandidateProfile3);
		candidate3.setAccount("abbc");
		candidate3.setChannel(channelService.findByChannelName("samsung"));
		candidate3.setLocation(locationService.findByLocationName("Ha Noi"));
		candidate3.setStatus(statusService.findByStatusName("Test - Fail"));
		candidate3
				.setAccount(personService.generateAccountFromName(candidate3.getTraineeCandidateProfile().getFullName()));

		Candidate candidate4 = new Candidate();
		candidate4.setTraineeCandidateProfile(traineeCandidateProfile4);
		candidate4.setAccount("abcc");
		candidate4.setChannel(channelService.findByChannelName("samsung"));
		candidate4.setLocation(locationService.findByLocationName("Ha Noi"));
		candidate4.setStatus(statusService.findByStatusName("Interview - Pass"));
		candidate4
				.setAccount(personService.generateAccountFromName(candidate4.getTraineeCandidateProfile().getFullName()));

		Candidate candidate5 = new Candidate();
		candidate5.setTraineeCandidateProfile(traineeCandidateProfile5);
		candidate1.setAccount("aabbc");
		candidate1.setChannel(channelService.findByChannelName("samsung"));
		candidate5.setLocation(locationService.findByLocationName("Ha Noi"));
		candidate5.setStatus(statusService.findByStatusName("Interview - Fail"));
		candidate5
				.setAccount(personService.generateAccountFromName(candidate5.getTraineeCandidateProfile().getFullName()));

		System.out.println(
				"Saved candidate: " + candidateService.save(candidate1).getTraineeCandidateProfile().getFullName());
		System.out.println(
				"Saved candidate: " + candidateService.save(candidate2).getTraineeCandidateProfile().getFullName());
		System.out.println(
				"Saved candidate: " + candidateService.save(candidate3).getTraineeCandidateProfile().getFullName());
		System.out.println(
				"Saved candidate: " + candidateService.save(candidate4).getTraineeCandidateProfile().getFullName());
		System.out.println(
				"Saved candidate: " + candidateService.save(candidate5).getTraineeCandidateProfile().getFullName());
	}
}

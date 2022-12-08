package com.example.demo.controller.traineemanagementcontroller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.ClassBatchProgression;
import com.example.demo.constants.DefaultPageSize;
import com.example.demo.controller.classmanagementcontroller.ClassManagementController;
import com.example.demo.entity.ClassBatch;
import com.example.demo.entity.Trainee;
import com.example.demo.model.ClassBatchViewModel;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.model.TraineeToChangeStatus;
import com.example.demo.model.TraineeUpdateModel;
import com.example.demo.service.iservice.IClassBatchService;
import com.example.demo.service.iservice.IFacultyService;
import com.example.demo.service.iservice.IStatusService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.service.iservice.IUniversityService;
import com.example.demo.utils.PaginationUtils;
import com.example.demo.utils.WebUtils;

@Controller
@RequestMapping("/trainee")
public class TraineeManagementController {

	@Autowired
	private ITraineeService traineeService;

	@Autowired
	private IClassBatchService classBatchService;

	@Autowired
	private IStatusService statusService;

	@Autowired
	private IFacultyService facultiService;

	@Autowired
	private IUniversityService universityService;

	@Autowired
	private ClassManagementController classView;

	@RequestMapping(path = { "/search_result", "/add_search_result" }, method = RequestMethod.POST)
	public String postSearchTraineePage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size,
			TraineeCriteriaModel traineeCriteriaModel, HttpSession session, HttpServletRequest request) {

		if (request.getRequestURI().toString().contains("add")) {
			List<Trainee> listOfTrainee = traineeService.filterTraineeSearchCriteriaToAdd(traineeCriteriaModel);
			System.out.println("list of trainee:" + listOfTrainee);
			session.setAttribute("listOfTrainee", listOfTrainee);
			return getSearchTraineePageToAdd(model, errorString, resultString, classBatchId, listOfTrainee, page, size);
		}
		List<Trainee> listOfTrainee = traineeService.filterTraineeSearchCriteriaInClass(traineeCriteriaModel,
				Integer.valueOf(classBatchId));
		System.out.println("list of trainee:" + listOfTrainee);
		session.setAttribute("listOfTrainee", listOfTrainee);
		return getSearchTraineePage(model, errorString, resultString, classBatchId, listOfTrainee, page, size);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void listTraineeSearchResult(Model model, String errorString, String resultString, String classBatchId,
			List<Trainee> listOfTrainee, Optional<Integer> page, Optional<Integer> size) {
		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);

		ClassBatch classBatch = classBatchService.findById(Integer.valueOf(classBatchId)).get();
		ClassBatchViewModel classBatchViewModel = new ClassBatchViewModel(classBatch);

		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listOfTrainee,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("traineePage", ((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("errorString", ((List<String>) paginationAttributes.get("errorString")).get(0));

		model.addAttribute("totalNumberOfTrainees", listOfTrainee.size());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("resultString", resultString);
		model.addAttribute("classBatchViewModel", classBatchViewModel);
		model.addAttribute("traineeCriteriaModel", new TraineeCriteriaModel());

		TraineeToChangeStatus listOfTraineeToChangeStatus = new TraineeToChangeStatus(
				((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0).getContent());
		model.addAttribute("traineeToChangeStatus", listOfTraineeToChangeStatus);
	}

	@RequestMapping(path = { "/search_result" }, method = RequestMethod.GET)
	public String getSearchTraineePage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "listOfTrainee", required = false) List<Trainee> listOfTrainee,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size) {
		listTraineeSearchResult(model, errorString, resultString, classBatchId, listOfTrainee, page, size);
		model.addAttribute("selectedTab", "trainee");
		return "/classManagement/class_view_paged";
	}

	@RequestMapping(path = { "/add_search_result" }, method = RequestMethod.GET)
	public String getSearchTraineePageToAdd(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "listOfTrainee", required = false) List<Trainee> listOfTrainee,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size) {
		listTraineeSearchResult(model, errorString, resultString, classBatchId, listOfTrainee, page, size);
		return "/traineeManagement/trainee_add";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@RequestMapping(path = { "/changeStatusInClass" }, method = RequestMethod.POST)
	public String getTraineeAddPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@SessionAttribute(name = "userFullName", required = false) String userFullName,
			TraineeToChangeStatus traineeToChangeStatus, HttpSession session, RedirectAttributes ra) {
		List<Trainee> listOfRetrievedTrainees = traineeToChangeStatus.getListOfTrainees();

		if (Objects.isNull(listOfRetrievedTrainees) || listOfRetrievedTrainees.size() < 1) {
			WebUtils.setRedirectAttributes(ra, "Please select at least 1 trainee.", resultString, classBatchId,
					"trainee");
			return "redirect:/classBatch/view";
		}
		for (Trainee retrievedTrainee : listOfRetrievedTrainees) {
			Trainee updateTrainee = traineeService.findById(retrievedTrainee.getId()).get();
			System.out.println("Update trainee " + updateTrainee.getAccount() + " from "
					+ updateTrainee.getStatusInClass() + " to " + retrievedTrainee.getStatusInClass());
			updateTrainee.setStatusInClass(retrievedTrainee.getStatusInClass());
			updateTrainee.setHistory(String.valueOf(new Date()) + " - Updated by " + userFullName);
			traineeService.save(updateTrainee);
		}
		ClassBatch classBatch = classBatchService.findById(Integer.valueOf(classBatchId)).get();
		classBatch.setActualTraineeNumber(classBatchService.calculateClassBatchActualTraineeNumber(classBatch));
		classBatchService.save(classBatch);
		WebUtils.setRedirectAttributes(ra, "Updated successfully", resultString, classBatchId, "trainee");
		return "redirect:/classBatch/view";

	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/removeFromClass" }, method = RequestMethod.POST)
	public String postTraineeRemovePage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@RequestParam(name = "selectedTrainee", required = false) String[] traineeIds, HttpSession session,
			RedirectAttributes ra) {

		List<String> classBatchRequiredStatuses = ClassBatchProgression.Submit.REQUIRED_STATUSES;
		ClassBatch classBatch = classBatchService.findById(Integer.valueOf(classBatchId)).get();

		if (!classBatchRequiredStatuses.contains(classBatch.getClassStatus().getClassStatusName())) {
			return classView.getViewClassPage(model, errorString,
					"Class status must be " + classBatchRequiredStatuses + " to remove trainee(s).", classBatchId,
					"class", Optional.ofNullable(null), Optional.ofNullable(null), session);
		}
		if (Objects.isNull(traineeIds) || traineeIds.length < 1) {
			return classView.getViewClassPage(model, "Please select at least 1 trainee to remove.", resultString,
					classBatchId, "trainee", Optional.ofNullable(null), Optional.ofNullable(null), session);
		}

		for (String traineeId : traineeIds) {
			Trainee trainee = traineeService.findById(Integer.valueOf(traineeId)).get();
			trainee.setClassBatch(null);
			trainee.setStatus(statusService.findByStatusName("Waiting for Class"));
			trainee.setStatusInClass("Drop-out");
			trainee.getTraineeCandidateProfile().setAllocationStatus("Not Allocated");
			traineeService.save(trainee);
		}
		classBatch.setAcceptedTraineeNumber(classBatch.getAcceptedTraineeNumber() - traineeIds.length);
		classBatch.setActualTraineeNumber(classBatchService.calculateClassBatchActualTraineeNumber(classBatch));
		classBatchService.save(classBatch);
		WebUtils.setRedirectAttributes(ra, "Trainee(s) removed successfully.", resultString, classBatchId, "trainee");
		return "redirect:/classBatch/view";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/addToClass" }, method = RequestMethod.GET)
	public String getTraineeAddPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@RequestParam(name = "classBatchId", required = false) String classBatchId,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> size, HttpSession session) {

		System.out.println("page: " + page);
		System.out.println("size: " + size);
		Integer currentPage = page.orElse(1);
		Integer pageSize = size.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);

		List<Trainee> listOfTrainee = traineeService.filterTraineeSearchCriteriaToAdd(new TraineeCriteriaModel());

		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listOfTrainee,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("traineePage", ((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("addTraineeErrorString", ((List<String>) paginationAttributes.get("errorString")).get(0));

		model.addAttribute("currentPage", currentPage);
		model.addAttribute("totalNumberOfTrainees", listOfTrainee.size());
		model.addAttribute("traineeCriteriaModel", new TraineeCriteriaModel());
		model.addAttribute("resultString", resultString);
		model.addAttribute("errorString", errorString);
		session.setAttribute("currentViewClassBatchId", classBatchId);
		return "/traineeManagement/trainee_add";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@RequestMapping(path = { "/addToClass" }, method = RequestMethod.POST)
	public String postTraineeAddPage(Model model,
			@RequestParam(name = "errorString", required = false) String errorString,
			@RequestParam(name = "resultString", required = false) String resultString,
			@SessionAttribute(name = "currentViewClassBatchId", required = false) String classBatchId,
			@RequestParam(name = "selectedTrainees", required = false) String[] traineeIds, HttpSession session,
			RedirectAttributes ra) {

		System.out.println(classBatchId);
		List<String> classBatchRequiredStatuses = ClassBatchProgression.Submit.REQUIRED_STATUSES;
		ClassBatch classBatch = classBatchService.findById(Integer.valueOf(classBatchId)).get();
		if (!classBatchRequiredStatuses.contains(classBatch.getClassStatus().getClassStatusName())) {
			WebUtils.setRedirectAttributes(ra, errorString,
					"Class status must be " + classBatchRequiredStatuses + " to add more trainee(s).", classBatchId,
					"class");
			return "redirect:/classBatch/view";
		}

		if (Objects.isNull(traineeIds) || traineeIds.length < 1) {
			return getTraineeAddPage(model, "Please select at least 1 trainee.", resultString, classBatchId,
					Optional.ofNullable(null), Optional.ofNullable(null), session);
		}

		for (String traineeId : traineeIds) {
			Trainee trainee = traineeService.findById(Integer.valueOf(traineeId)).get();
			trainee.setClassBatch(classBatch);
			trainee.setStatus(statusService.findByStatusName("Enrolled"));
			trainee.setStatusInClass("Enrolled");
			trainee.getTraineeCandidateProfile().setAllocationStatus("Not Allocated");
			traineeService.save(trainee);
		}
		classBatch.setAcceptedTraineeNumber(classBatch.getAcceptedTraineeNumber() + traineeIds.length);
		classBatchService.save(classBatch);
		WebUtils.setRedirectAttributes(ra, errorString, "Trainee(s) added successfully.", classBatchId, "class");
		return "redirect:/classBatch/view";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER')")
	@PostMapping("/delete")
	public String delete(Model model, @RequestParam(name = "selectedTrainee", required = false) String[] traineeIds,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize) {

		String errorString = "";

		if (traineeIds != null) {
			for (String traineeId : traineeIds) {
				traineeService.deleteTrainee(traineeId);
			}
		} else {
			errorString = "No trainees selected!";
		}

		return listAll(model, page, requestSize, errorString);
	}

	@GetMapping("/delete")
	public String getDelete(Model model,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize) {
	
		return listAll(model, page, requestSize, "");
	}

	@GetMapping("/delete/{id}")
	public String deleteById(Model model, @PathVariable(name = "id") Integer id,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString) {

		try {
			traineeService.deleteTrainee(id.toString());
		} catch (Exception e) {
			System.out.println("trainee deleted!");
		}
		
		return listAll(model, page, requestSize, "");
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void listTraineeData(Model model, List<Trainee> listOfTrainee, Integer currentPage, Integer pageSize) {
		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listOfTrainee,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("traineePage", ((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("errorString", ((List<String>) paginationAttributes.get("errorString")).get(0));

		model.addAttribute("totalNumberOfTrainees", listOfTrainee.size());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("size", pageSize);
		model.addAttribute("traineeCriteriaModel", new TraineeCriteriaModel());
	}
	
	@GetMapping("/listing")
	public String listAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString) {

		Integer currentPage = page.orElse(1);
		Integer pageSize = requestSize.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);
		List<Trainee> listOfTrainee = traineeService.findAll();

		if (listOfTrainee.size() == 0) {
			errorString = "No results found!";
		}
		listTraineeData(model, listOfTrainee, currentPage, pageSize);
		return "/traineeManagement/trainee_listing";
	}
	
	@GetMapping("/search_all")
	public String getSeacrhAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString, HttpSession session, 
			@SessionAttribute(name = "listOfSearchTrainee", required = false) List<Trainee> listOfTrainee,
			TraineeCriteriaModel traineeCriteriaModel) {
		
		Integer currentPage = page.orElse(1);
		Integer pageSize = requestSize.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);

		if (listOfTrainee.size() == 0) {
			errorString = "No results found!";
		}
		listTraineeData(model, listOfTrainee, currentPage, pageSize);
		return "/traineeManagement/trainee_listing";
	}
	
	@PostMapping("/search_all")
	public String searchAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString, HttpSession session, 
			TraineeCriteriaModel traineeCriteriaModel) {
		
		List<Trainee> listOfTrainee = traineeService.searchListTrainee(traineeCriteriaModel);
		for (Trainee trainee : listOfTrainee) {
			System.out.println(trainee.toString());
		}
		System.out.println("list of trainee:" + listOfTrainee);
		session.setAttribute("listOfSearchTrainee", listOfTrainee);
		return getSeacrhAll(model, page, requestSize, errorString, session, listOfTrainee, traineeCriteriaModel);
	}

	@GetMapping("/view_info/{id}")
	public String viewInfo(Model model, @PathVariable(name = "id") Integer id) {

		Trainee trainee = traineeService.findById(id).get();
		model.addAttribute("trainee", trainee);
		return "/traineeManagement/trainee_info";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@GetMapping("/update/{id}")
	public String updateInfo(Model model, @PathVariable(name = "id", required = false) Integer id) {

		model.addAttribute("traineeUpdateModel", new TraineeUpdateModel());
		model.addAttribute("trainee", traineeService.findById(id).get());
		model.addAttribute("universities", universityService.findAll());
		model.addAttribute("faculties", facultiService.findAll());

		return "/traineeManagement/trainee_update";
	}

	/**
	 * Update trainee from listing page
	 */
	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@PostMapping("/update")
	public String update(Model model, @RequestParam(name = "selectedTrainee", required = false) String[] traineeIds,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize) {

		String errorString = "";
		Integer id = 0;

		if (traineeIds != null) {
			if (traineeIds.length > 1) {
				errorString = "Please select 1 trainee to update!";
				return listAll(model, page, requestSize, errorString);
			} else {
				id = Integer.parseInt(traineeIds[0]);
			}
		} else {
			errorString = "Please select 1 trainee to update!";
			return listAll(model, page, requestSize, errorString);
		}

		model.addAttribute("traineeUpdateModel", new TraineeUpdateModel());
		model.addAttribute("trainee", traineeService.findById(id).get());
		model.addAttribute("universities", universityService.findAll());
		model.addAttribute("faculties", facultiService.findAll());

		return "/traineeManagement/trainee_update";
	}
	
	@GetMapping("/update")
	public String getUpdate(Model model,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize) {
	
		return listAll(model, page, requestSize, "");
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','DELIVERY_MANAGER','ADMIN')")
	@PostMapping("/update/{id}")
	public String updateInfo(Model model, @PathVariable(name = "id") Integer id,
			TraineeUpdateModel traineeUpdateModel) {

		System.out.println(traineeUpdateModel.toString());
		String errorString = "";
		try {
			traineeService.update(traineeUpdateModel);
		} catch (Exception e) {
			errorString = "Invalid data";
			
			model.addAttribute("traineeUpdateModel", new TraineeUpdateModel());
			model.addAttribute("trainee", traineeService.findById(id).get());
			model.addAttribute("universities", universityService.findAll());
			model.addAttribute("faculties", facultiService.findAll());
			model.addAttribute("errorString", errorString);
			
			return "/traineeManagement/trainee_update";
		}
		
		return viewInfo(model, id);
	}

}

package com.example.demo.controller.candidatemanagementcontroller;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.constants.DefaultPageSize;
import com.example.demo.entity.Candidate;
import com.example.demo.entity.Channel;
import com.example.demo.entity.Faculty;
import com.example.demo.entity.Location;
import com.example.demo.entity.Status;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.TraineeCandidateProfile;
import com.example.demo.entity.University;
import com.example.demo.model.CandidateCriteriaModel;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.service.CandidateService;
import com.example.demo.service.FileService;
import com.example.demo.service.LocationService;
import com.example.demo.service.StatusService;
import com.example.demo.service.iservice.IChannelService;
import com.example.demo.service.iservice.IFacultyService;
import com.example.demo.service.iservice.ILocationService;
import com.example.demo.service.iservice.IPersonService;
import com.example.demo.service.iservice.IUniversityService;
import com.example.demo.utils.DateUtils;
import com.example.demo.utils.HibernateValidator;
import com.example.demo.utils.PaginationUtils;

@Controller
@RequestMapping("/candidate")
public class CandidateManagementController {

	@Autowired
	CandidateService candidateService;

	@Autowired
	IChannelService channelService;

	@Autowired
	ILocationService locationService;
	
	@Autowired
	IPersonService personService;

	@Autowired
	IUniversityService universityService;

	@Autowired
	IFacultyService facultyService;

	@Autowired
	FileService fileService;
	
	@Autowired
	StatusService statusService;
	

	@PreAuthorize("hasAnyRole('FA_MANAGER','FA_REC')")
	@GetMapping("/create")
	public String getCreateCandidatePage(Model model,
			@SessionAttribute(name = "userFullName", required = false) String userFullName) {
		model.addAttribute("channels", channelService.getAllChannels());
		model.addAttribute("locations", locationService.findAll());
		model.addAttribute("universities", universityService.findAll());
		model.addAttribute("faculties", facultyService.findAll());
		CandidateCriteriaModel candidateCriteriaModel = new CandidateCriteriaModel();
		candidateCriteriaModel.setId(Integer.valueOf(candidateService.getMaxIdValue() + 1));
		candidateCriteriaModel.setType("Candidate");
		candidateCriteriaModel.setStatus("New");
		candidateCriteriaModel.setHistory(String.valueOf(new Date()) + " - created by " + userFullName);
		// create model for view
		model.addAttribute("candidateCriteriaModel", candidateCriteriaModel);

		return "/candidateManagement/candidate_addition";
	}

	/**
	 * Save candidate information into database Using POST method
	 * 
	 * @param model
	 * @param candidateCriteriaModel - candidate information is taken from user
	 * @param fileCV                 - multipart type and is saved into
	 * @return
	 */
	@PreAuthorize("hasAnyRole('FA_MANAGER','FA_REC')")
	@PostMapping("/create")
	public String createCandidate(Model model, CandidateCriteriaModel candidateCriteriaModel,
			@RequestParam("FileCV") MultipartFile fileCV) {
		String message = "";
		System.out.println(candidateCriteriaModel);

		// Create TraineeCandidateProfile
		TraineeCandidateProfile traineeCandidateProfile = new TraineeCandidateProfile();
		traineeCandidateProfile.setFullName(candidateCriteriaModel.getFullName());
		traineeCandidateProfile
				.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString(candidateCriteriaModel.getDateOfBirth()));
		traineeCandidateProfile.setGender(candidateCriteriaModel.getGender());

		traineeCandidateProfile
				.setUniversity(universityService.findByUniversityName(candidateCriteriaModel.getUniversity()));
		traineeCandidateProfile.setFaculty(facultyService.findByFacultyName(candidateCriteriaModel.getFaculty()));
		traineeCandidateProfile
				.setGraduationYear(DateUtils.parseDDMMYYYYDateFromString(candidateCriteriaModel.getGraduationYear()));
		traineeCandidateProfile.setPhone(candidateCriteriaModel.getPhone());
		traineeCandidateProfile.setEmail(candidateCriteriaModel.getEmail());
		traineeCandidateProfile.setType("Candidate");
		traineeCandidateProfile.setSkill(candidateCriteriaModel.getSkill());
		traineeCandidateProfile.setForeignLanguage(candidateCriteriaModel.getForeignLanguage());
		traineeCandidateProfile.setCV(fileService.uploadFile(fileCV));
		traineeCandidateProfile.setLevel(candidateCriteriaModel.getLevel());
		
		if (!Objects.isNull(message = HibernateValidator.validateEntityAndGetMessage(traineeCandidateProfile))) {
			model.addAttribute("message", message);
			return "/candidateManagement/candidate_addition";
		}
		// Create Candidate
		Candidate candidate = new Candidate();
		candidate.setTraineeCandidateProfile(traineeCandidateProfile);
		candidate.setStatus(statusService.findByStatusName("New"));
		candidate.setAccount(candidateCriteriaModel.getAccount());
		candidate
				.setApplicationDate(DateUtils.parseDDMMYYYYDateFromString(candidateCriteriaModel.getApplicationDate()));
		// TODO create channel set
		candidate.setChannel(channelService.findByChannelName(candidateCriteriaModel.getChannel()));
		candidate.setLocation(locationService.findByLocationName(candidateCriteriaModel.getLocation()));
		candidate.setRemarks(candidateCriteriaModel.getNote());
		candidate.setHistory(candidateCriteriaModel.getHistory());
		personService.generateAccount(candidate);
		if (!Objects.isNull(message = HibernateValidator.validateEntityAndGetMessage(candidate))) {
			model.addAttribute("message", message);
			return "/candidateManagement/candidate_addition";
		}
		// Save Candidate and TraineeCandidateProfile into database
		candidateService.save(candidate);

		return "redirect:/candidate/listing";
	}
	
	@PreAuthorize("hasAnyRole('FA_MANAGER','FA_REC')")
	@GetMapping("/update")
	public String getUpdateCandidatePage(Model model) {
		
		return "/candidateManagement/candidate_view";
	}

	@Autowired
	CandidateRepository candidateRepository;

	/**
	 * Display candidate information view
	 * 
	 * @param model
	 * @param id    - candidate id field
	 * @return
	 */
	@GetMapping("/view")
	public String getCandidateView(Model model, @RequestParam(name = "id") Integer id) {
		
		// Set CandidateCriteriaModel
		CandidateCriteriaModel ccm = new CandidateCriteriaModel();
		String message = "";
		
		Candidate candidate = candidateService.findById(id);
		if (Objects.isNull(candidate)) {
			message = "There are some problem in here! We cannot find candidate!";
			return "/candidateManagement/candidate_view";
		}
		// TODO multiple fields in candidate
		ccm.setId(id);
		String application = Objects.isNull(candidate.getApplicationDate()) ? " " : candidate.getApplicationDate().toString();
		ccm.setApplicationDate(application);
		// TODO check null pointer
		String channel = Objects.isNull(candidate.getChannel()) ? " " : candidate.getChannel().getChannelName();
		ccm.setChannel(channel);
		// TODO check null pointer
		String location = Objects.isNull(candidate.getLocation()) ? " " : candidate.getLocation().getLocationName();
		ccm.setLocation(location);
		ccm.setHistory(candidate.getHistory());
		// TODO check null pointer
		String status = Objects.isNull(candidate.getStatus()) ? " " : candidate.getStatus().getStatusName();
		ccm.setStatus(status);
		ccm.setAccount(candidate.getAccount());

		// TODO multiple fields in TraineeCandidateProfile
		TraineeCandidateProfile tcp = candidate.getTraineeCandidateProfile();
		if (Objects.isNull(tcp)) {
			return "/candidateManagement/candidate_view";
		}
		ccm.setType(tcp.getType());
		ccm.setFullName(tcp.getFullName());
		ccm.setGender(tcp.getGender());
		String dateOfBirth = Objects.isNull(tcp.getDateOfBirth()) ? " " : tcp.getDateOfBirth().toString();
		ccm.setDateOfBirth(dateOfBirth);
		ccm.setCv(tcp.getCV());
		// TODO check null pointer
		String university = Objects.isNull(tcp.getUniversity()) ? " " : tcp.getUniversity().getUniversityName();
		ccm.setUniversity(university);
		// TODO check null pointer
		String faculty = Objects.isNull(tcp.getFaculty()) ? " " : tcp.getFaculty().getFacultyName();
		ccm.setFaculty(faculty);
		ccm.setPhone(tcp.getPhone());
		ccm.setEmail(tcp.getEmail());
		ccm.setSkill(tcp.getSkill());
		
		@SuppressWarnings("deprecation")
		String graduationYear = Objects.isNull(tcp.getGraduationYear()) ? " " : String.valueOf(tcp.getGraduationYear().getYear());
		ccm.setGraduationYear(graduationYear);
		ccm.setForeignLanguage(tcp.getForeignLanguage());
		ccm.setNote(candidate.getRemarks());
		ccm.setLevel(tcp.getLevel());

		model.addAttribute("candidateCriteriaModel", ccm);
		model.addAttribute("message", message);
		return "/candidateManagement/candidate_view";
	}

	@PreAuthorize("hasAnyRole('FA_MANAGER','FA_REC')")
	@PostMapping("/delete")
	public String delete(Model model, @RequestParam(name = "selectedCandidate", required = false) String[] candidateIds,
			@RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize, RedirectAttributes ra) {

		if (candidateIds != null) {
			for (String candidateId : candidateIds) {
				candidateService.deleteCandidate(candidateId);
			}
		} else {
			ra.addFlashAttribute("errorString", "No candidate selected!");
		}

		List<Candidate> listCandidates = candidateService.findAll();
		if (listCandidates.size() == 0) {
			ra.addFlashAttribute("errorString", "All candidates deleted!");
		}

		return "redirect:/candidate/listing";
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void listCandidateData(Model model, List<Candidate> listCandidates, Integer currentPage, Integer pageSize) {
		Map<String, Collection> paginationAttributes = PaginationUtils.getPaginationAttributes(listCandidates,
				currentPage, pageSize);

		model.addAttribute("pageNumbers", paginationAttributes.get("pageNumbers"));
		model.addAttribute("candidatePage", ((List<Page<Trainee>>) paginationAttributes.get("entityPage")).get(0));
		model.addAttribute("startNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("startNumberOfCurrentPage")).get(0));
		model.addAttribute("endNumberOfCurrentPage",
				((List<Integer>) paginationAttributes.get("endNumberOfCurrentPage")).get(0));
		model.addAttribute("totalNumberOfCandidates", listCandidates.size());
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("size", pageSize);
		model.addAttribute("candidateCriteriaModel", new CandidateCriteriaModel());
	}

	@GetMapping({ "/listing" })
	public String listAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@ModelAttribute(name = "candidateCriteriaModel") CandidateCriteriaModel candidateCriteriaModel) {
		Integer currentPage = page.orElse(1);
		Integer pageSize = requestSize.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);
		List<Candidate> listCandidates = candidateService.findAll();
		if (listCandidates.size() == 0) {
			model.addAttribute("errorString", "No results found!");
		}
		listCandidateData(model, listCandidates, currentPage, pageSize);
		return "/candidateManagement/candidate_list";
	}

	@GetMapping({ "/search" })
	public String getSearchAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString,
			@SessionAttribute(name = "listOfSearchCandidates", required = false) List<Candidate> listOfCandidates,
			@ModelAttribute(name = "candidateCriteriaModel") CandidateCriteriaModel candidateCriteriaModel) {
		Integer currentPage = page.orElse(1);
		Integer pageSize = requestSize.orElse(DefaultPageSize.DEFAULT_PAGE_SIZE);
		if (listOfCandidates.size() == 0) {
			model.addAttribute("errorString", "No results found!");
		}
		listCandidateData(model, listOfCandidates, currentPage, pageSize);
		return "/candidateManagement/candidate_list";
	}

	@PostMapping("/search")
	public String searchAll(Model model, @RequestParam(name = "page", required = false) Optional<Integer> page,
			@RequestParam(name = "size", required = false) Optional<Integer> requestSize,
			@RequestParam(name = "errorString", required = false) String errorString, HttpSession session,
			CandidateCriteriaModel candidateCriteriaModel) {

		List<Candidate> listOfCandidates = candidateService.searchListCandidates(candidateCriteriaModel);
		for (Candidate candidate : listOfCandidates) {
			System.out.println(candidate.toString());
		}
		session.setAttribute("listOfSearchCandidates", listOfCandidates);
		return getSearchAll(model, page, requestSize, errorString, listOfCandidates, candidateCriteriaModel);
	}
}

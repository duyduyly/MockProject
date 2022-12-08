package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.constants.DefaultPageSize;
import com.example.demo.entity.Trainee;
import com.example.demo.model.TraineeCriteriaModel;
import com.example.demo.model.TraineeUpdateModel;
import com.example.demo.repository.TraineeRepository;
import com.example.demo.service.iservice.IPersonService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.utils.DateUtils;

@Service
public class TraineeService implements ITraineeService {

	@Autowired
	private TraineeRepository traineeRepository;

	@Autowired
	IPersonService personService;

	@Override
	public List<Trainee> filterTraineeSearchCriteria(TraineeCriteriaModel traineeCriteriaModel) {
		Date dateOfBirth = null;
		Integer id = null;

		traineeCriteriaModel = checkCriteria(traineeCriteriaModel);
		dateOfBirth = checkDateCriteria(traineeCriteriaModel);
		id = checkIdCriteria(traineeCriteriaModel);

		return traineeRepository.findAllBySearchCriteria(id, traineeCriteriaModel.getAccount(),
				traineeCriteriaModel.getName(), dateOfBirth, traineeCriteriaModel.getPhone(),
				traineeCriteriaModel.getEmail());
	}

	@Override
	public List<Trainee> filterTraineeSearchCriteriaToAdd(TraineeCriteriaModel traineeCriteriaModel) {
		Date dateOfBirth = null;
		Integer id = null;

		traineeCriteriaModel = checkCriteria(traineeCriteriaModel);
		dateOfBirth = checkDateCriteria(traineeCriteriaModel);
		id = checkIdCriteria(traineeCriteriaModel);

		return traineeRepository.findAllBySearchCriteriaToAdd(id, traineeCriteriaModel.getAccount(),
				traineeCriteriaModel.getName(), dateOfBirth, traineeCriteriaModel.getPhone(),
				traineeCriteriaModel.getEmail(), "Waiting for Class", "Waiting for Allocation", "Deferred", "Drop-out");
	}

	@Override
	public List<Trainee> filterTraineeSearchCriteriaInClass(TraineeCriteriaModel traineeCriteriaModel,
			Integer classBatchId) {
		Date dateOfBirth = null;
		Integer id = null;

		traineeCriteriaModel = checkCriteria(traineeCriteriaModel);
		dateOfBirth = checkDateCriteria(traineeCriteriaModel);
		id = checkIdCriteria(traineeCriteriaModel);

		return traineeRepository.findAllBySearchCriteriaInClass(id, traineeCriteriaModel.getAccount(),
				traineeCriteriaModel.getName(), dateOfBirth, traineeCriteriaModel.getPhone(),
				traineeCriteriaModel.getEmail(), classBatchId);
	}

	private TraineeCriteriaModel checkCriteria(TraineeCriteriaModel traineeCriteriaModel) {

		if (Objects.isNull(traineeCriteriaModel.getAccount()) || traineeCriteriaModel.getAccount().isEmpty()
				|| traineeCriteriaModel.getAccount().isBlank()) {
			traineeCriteriaModel.setAccount(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getName()) || traineeCriteriaModel.getName().isEmpty()
				|| traineeCriteriaModel.getName().isBlank()) {
			traineeCriteriaModel.setName(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getPhone()) || traineeCriteriaModel.getPhone().isEmpty()
				|| traineeCriteriaModel.getPhone().isBlank()) {
			traineeCriteriaModel.setPhone(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getEmail()) || traineeCriteriaModel.getEmail().isEmpty()
				|| traineeCriteriaModel.getEmail().isBlank()) {
			traineeCriteriaModel.setEmail(null);
		}

		return traineeCriteriaModel;
	}

	private Date checkDateCriteria(TraineeCriteriaModel traineeCriteriaModel) {
		System.out.println(traineeCriteriaModel.toString());
		if (Objects.isNull(traineeCriteriaModel.getDateOfBirth()) || traineeCriteriaModel.getDateOfBirth().isEmpty()
				|| traineeCriteriaModel.getDateOfBirth().isBlank()) {
			return null;
		} else {
			return DateUtils.parseDDMMYYYYDateFromString(traineeCriteriaModel.getDateOfBirth());
		}
	}

	private Integer checkIdCriteria(TraineeCriteriaModel traineeCriteriaModel) {
		if (Objects.isNull(traineeCriteriaModel.getEmplId()) || traineeCriteriaModel.getEmplId().isEmpty()
				|| traineeCriteriaModel.getEmplId().isBlank()) {
			return null;
		} else {
			try {
				return Integer.valueOf(traineeCriteriaModel.getEmplId());
			} catch (Exception e) {
				return null;
			}
		}
	}

	/**
	 * Tìm kiếm trainee theo traineeCriteriaModel. Attribute nào không có thì cho là
	 * null
	 */
	@Override
	public Page<Trainee> searchTrainee(int currentPage, int size, TraineeCriteriaModel traineeCriteriaModel) {
		Pageable pageable = PageRequest.of(currentPage - 1, DefaultPageSize.DEFAULT_PAGE_SIZE);

		Date dateOfBirth = new Date();
		Integer id = checkIdCriteria(traineeCriteriaModel);
		if (Objects.isNull(traineeCriteriaModel.getName()) || traineeCriteriaModel.getName().isBlank()
				|| traineeCriteriaModel.getName().isEmpty()) {
			traineeCriteriaModel.setName(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getDateOfBirth()) || traineeCriteriaModel.getDateOfBirth().isEmpty()
				|| traineeCriteriaModel.getDateOfBirth().isBlank()) {
			dateOfBirth = null;
		} else {
			dateOfBirth = DateUtils.parseDDMMYYYYDateFromString(traineeCriteriaModel.getDateOfBirth());
		}

		if (Objects.isNull(traineeCriteriaModel.getPhone()) || traineeCriteriaModel.getPhone().isBlank()
				|| traineeCriteriaModel.getPhone().isEmpty()) {
			traineeCriteriaModel.setPhone(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getEmail()) || traineeCriteriaModel.getEmail().isBlank()
				|| traineeCriteriaModel.getEmail().isEmpty()) {
			traineeCriteriaModel.setEmail(null);
		}
		if (Objects.isNull(traineeCriteriaModel.getAccount()) || traineeCriteriaModel.getAccount().isBlank()
				|| traineeCriteriaModel.getAccount().isEmpty()) {
			traineeCriteriaModel.setAccount(null);
		}

		return traineeRepository.searchTrainee(id, traineeCriteriaModel.getName(), traineeCriteriaModel.getAccount(),
				dateOfBirth, traineeCriteriaModel.getPhone(), traineeCriteriaModel.getEmail(), pageable);
	}

	@Override
	public List<Trainee> searchListTrainee(TraineeCriteriaModel traineeCriteriaModel) {

		Date dateOfBirth = new Date();
		Integer id = checkIdCriteria(traineeCriteriaModel);
		if (Objects.isNull(traineeCriteriaModel.getName()) || traineeCriteriaModel.getName().isBlank()
				|| traineeCriteriaModel.getName().isEmpty()) {
			traineeCriteriaModel.setName(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getDateOfBirth()) || traineeCriteriaModel.getDateOfBirth().isEmpty()
				|| traineeCriteriaModel.getDateOfBirth().isBlank()) {
			dateOfBirth = null;
		} else {
			dateOfBirth = DateUtils.parseDDMMYYYYDateFromString(traineeCriteriaModel.getDateOfBirth());
		}

		if (Objects.isNull(traineeCriteriaModel.getPhone()) || traineeCriteriaModel.getPhone().isBlank()
				|| traineeCriteriaModel.getPhone().isEmpty()) {
			traineeCriteriaModel.setPhone(null);
		}

		if (Objects.isNull(traineeCriteriaModel.getEmail()) || traineeCriteriaModel.getEmail().isBlank()
				|| traineeCriteriaModel.getEmail().isEmpty()) {
			traineeCriteriaModel.setEmail(null);
		}
		if (Objects.isNull(traineeCriteriaModel.getAccount()) || traineeCriteriaModel.getAccount().isBlank()
				|| traineeCriteriaModel.getAccount().isEmpty()) {
			traineeCriteriaModel.setAccount(null);
		}

		return traineeRepository.searchListTrainee(id, traineeCriteriaModel.getName(),
				traineeCriteriaModel.getAccount(), dateOfBirth, traineeCriteriaModel.getPhone(),
				traineeCriteriaModel.getEmail());
	}

	@Override
	public Page<Trainee> findAllTrainee(int currentPage, int size) {
		Pageable pageable = PageRequest.of(currentPage - 1, size);
		return traineeRepository.findAll(pageable);
	}

	@Override
	public void deleteTrainee(String stringTraineeId) {
		Integer traineeId = Integer.valueOf(stringTraineeId);
		traineeRepository.delete(traineeRepository.findById(traineeId).get());
	}

	@Override
	public Optional<Trainee> findById(Integer id) {
		return traineeRepository.findById(id);
	}

	@Override
	public List<Trainee> findAllBySearchCriteria(Integer id, String account, String fullName, Date dateOfBirth,
			String phone, String email) {
		return traineeRepository.findAllBySearchCriteria(id, account, fullName, dateOfBirth, phone, email);
	}

	@Override
	public List<Trainee> findAllBySearchCriteriaInClass(Integer id, String account, String fullName, Date dateOfBirth,
			String phone, String email, Integer classBatchId) {
		return traineeRepository.findAllBySearchCriteriaInClass(id, account, fullName, dateOfBirth, phone, email,
				classBatchId);
	}

	@Override
	public List<Trainee> findAllBySearchCriteriaToAdd(Integer id, String account, String fullName, Date dateOfBirth,
			String phone, String email, String waitingForClass, String waitingForAllocation, String deferred,
			String dropOut) {
		return traineeRepository.findAllBySearchCriteriaToAdd(id, account, fullName, dateOfBirth, phone, email,
				waitingForClass, waitingForAllocation, deferred, dropOut);
	}

	@Override
	public Long countByAccountContaining(String account) {
		return traineeRepository.countByAccountContaining(account);
	}

	@Override
	public Long countByStatusInClassIsAndClassBatch_IdIs(String statusInClass, Integer classBatchId) {
		return traineeRepository.countByStatusInClassIsAndClassBatch_IdIs(statusInClass, classBatchId);
	}

	@Override
	public Page<Trainee> searchTrainee(Integer id, String fullName, String account, Date dateOfBirth, String phone,
			String email, Pageable pageable) {
		return traineeRepository.searchTrainee(id, fullName, account, dateOfBirth, phone, email, pageable);
	}

	@Override
	public Trainee save(Trainee trainee) {
		return traineeRepository.save(trainee);
	}

	@Override
	public void update(TraineeUpdateModel traineeUpdateModel) {

		Trainee trainee = traineeRepository.findById(Integer.parseInt(traineeUpdateModel.getId())).get();

		if (Objects.isNull(traineeUpdateModel.getFullName()) || traineeUpdateModel.getFullName().isBlank()
				|| traineeUpdateModel.getFullName().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().setFullName(traineeUpdateModel.getFullName());
			personService.generateAccount(trainee);
		}

		if (Objects.isNull(traineeUpdateModel.getDateOfBirth()) || traineeUpdateModel.getDateOfBirth().isBlank()
				|| traineeUpdateModel.getDateOfBirth().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile()
					.setDateOfBirth(DateUtils.parseDDMMYYYYDateFromString(traineeUpdateModel.getDateOfBirth()));
		}

		if (Objects.isNull(traineeUpdateModel.getGender()) || traineeUpdateModel.getGender().isBlank()
				|| traineeUpdateModel.getGender().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().setGender(traineeUpdateModel.getGender());
		}

		if (Objects.isNull(traineeUpdateModel.getUniversity()) || traineeUpdateModel.getUniversity().isBlank()
				|| traineeUpdateModel.getUniversity().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().getUniversity().setUniversityName(traineeUpdateModel.getUniversity());
		}

		if (Objects.isNull(traineeUpdateModel.getFaculty()) || traineeUpdateModel.getFaculty().isBlank()
				|| traineeUpdateModel.getFaculty().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().getFaculty().setFacultyName(traineeUpdateModel.getFaculty());
		}

		if (Objects.isNull(traineeUpdateModel.getPhone()) || traineeUpdateModel.getPhone().isBlank()
				|| traineeUpdateModel.getPhone().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().setPhone(traineeUpdateModel.getPhone());
		}

		if (Objects.isNull(traineeUpdateModel.getEmail()) || traineeUpdateModel.getEmail().isBlank()
				|| traineeUpdateModel.getEmail().isEmpty()) {
		} else {
			trainee.getTraineeCandidateProfile().setEmail(traineeUpdateModel.getEmail());
		}

		traineeRepository.save(trainee);

	}

	@Override
	public List<Trainee> searchListTrainee(Integer id, String fullName, String account, Date dateOfBirth, String phone,
			String email) {
		return traineeRepository.searchListTrainee(id, fullName, account, dateOfBirth, phone, email);
	}

	@Override
	public List<Trainee> findAll() {
		return traineeRepository.findAll();
	}
	
	@Override
	public List<Trainee> saveAll(List<Trainee> listOfTrainees){
		return traineeRepository.saveAll(listOfTrainees);
	}

	@Override
	public boolean checkIfTraineeExisted(Trainee trainee) {
		return (traineeRepository.existsByTraineeCandidateProfile_Email(trainee.getTraineeCandidateProfile().getEmail())
				|| traineeRepository
						.existsByTraineeCandidateProfile_Phone(trainee.getTraineeCandidateProfile().getPhone()));
	}

}

package com.example.demo.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BaseEntity;
import com.example.demo.entity.Candidate;
import com.example.demo.entity.ClassAdmin;
import com.example.demo.entity.DeliveryManager;
import com.example.demo.entity.FAManager;
import com.example.demo.entity.FARec;
import com.example.demo.entity.Trainee;
import com.example.demo.entity.Trainer;
import com.example.demo.service.iservice.ICandidateService;
import com.example.demo.service.iservice.IClassAdminService;
import com.example.demo.service.iservice.IDeliveryManagerService;
import com.example.demo.service.iservice.IFAManagerService;
import com.example.demo.service.iservice.IFARecService;
import com.example.demo.service.iservice.IPersonService;
import com.example.demo.service.iservice.ITraineeService;
import com.example.demo.service.iservice.ITrainerService;

@Service
public class PersonService implements IPersonService {

	@Autowired
	private ITrainerService trainerService;

	@Autowired
	private ITraineeService traineeService;

	@Autowired
	private IClassAdminService classAdminService;

	@Autowired
	private IFAManagerService faManagerService;

	@Autowired
	private IFARecService faRecService;

	@Autowired
	private IDeliveryManagerService deliManagerService;

	@Autowired
	private ICandidateService candidateService;

	@Override
	public <T extends BaseEntity> void generateAccount(T person) {
		if (person instanceof Trainer) {
			((Trainer) person)
					.setAccount(generateAccountFromName(((Trainer) person).getTrainerProfile().getFullName()));
		} else if (person instanceof ClassAdmin) {
			((ClassAdmin) person)
					.setAccount(generateAccountFromName(((ClassAdmin) person).getClassAdminProfile().getFullName()));
		} else if (person instanceof FAManager) {
			((FAManager) person).setAccount(generateAccountFromName(((FAManager) person).getFullName()));
		} else if (person instanceof FARec) {
			((FARec) person).setAccount(generateAccountFromName(((FARec) person).getFullName()));
		} else if (person instanceof DeliveryManager) {
			((DeliveryManager) person).setAccount(generateAccountFromName(((DeliveryManager) person).getFullName()));
		} else if (person instanceof Trainee) {
			((Trainee) person)
					.setAccount(generateAccountFromName(((Trainee) person).getTraineeCandidateProfile().getFullName()));
		} else if (person instanceof Candidate) {
			((Candidate) person).setAccount(
					generateAccountFromName(((Candidate) person).getTraineeCandidateProfile().getFullName()));
		}
	}

	@Override
	public String generateAccountFromName(String inputString) {
		List<String> splittedString = Arrays.asList(inputString.split(" "));
		StringBuilder resultString = new StringBuilder();
		resultString.append(splittedString.get(splittedString.size() - 1));
		for (int i = 0; i < splittedString.size() - 1; i++) {
			resultString.append(splittedString.get(i).charAt(0));
		}
		Long countExistedAccount = checkExistedAccount(resultString.toString());
		if (countExistedAccount < 1) {
			return resultString.toString();
		}
		return resultString.append(countExistedAccount + 1).toString();
	}

	@Override
	public Long checkExistedAccount(String account) {
		Long result = 0l;
		result = faManagerService.countByAccountContaining(account) + faRecService.countByAccountContaining(account)
				+ deliManagerService.countByAccountContaining(account)
				+ trainerService.countByAccountContaining(account) + classAdminService.countByAccountContaining(account)
				+ traineeService.countByAccountContaining(account) + candidateService.countByAccountContaining(account);
		return result;
	}

	@Override
	public <T extends BaseEntity> boolean checkIfPersonExisted(T person) {
		if (person instanceof Trainer) {
			return trainerService.checkIfTrainerExisted((Trainer) person);
		} else if (person instanceof ClassAdmin) {
			return classAdminService.checkIfClassAdminExisted((ClassAdmin)person);
		} else if (person instanceof FAManager) {
			return faManagerService.checkIfFAManagerExisted((FAManager)person);
		} else if (person instanceof FARec) {
			return faRecService.checkIfFArecExisted((FARec)person);
		} else if (person instanceof DeliveryManager) {
			return deliManagerService.checkIfDeliManagerExisted((DeliveryManager)person);
		} else if (person instanceof Trainee) {
			return traineeService.checkIfTraineeExisted((Trainee)person);
		} else if (person instanceof Candidate) {
			return candidateService.checkIfCandidateExisted((Candidate)person);
		}
		return false;
	}
	
	@Override
	public <T extends BaseEntity> boolean checkIfPersonInListExisted(List<T> listOfPersons) {
		for (T person : listOfPersons) {
			if (checkIfPersonExisted(person)) {
				return true;
			}
		}
		return false;
	}
}

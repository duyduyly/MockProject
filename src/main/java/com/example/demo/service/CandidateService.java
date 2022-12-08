package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Candidate;
import com.example.demo.model.CandidateCriteriaModel;
import com.example.demo.repository.CandidateRepository;
import com.example.demo.service.iservice.ICandidateService;
import com.example.demo.utils.DateUtils;

@Service
public class CandidateService implements ICandidateService {

	@Autowired
	private CandidateRepository candidateRepository;
	
	public Integer getMaxIdValue() {
		return candidateRepository.getMaxCandidateId();
	}

	@Override
	public Page<Candidate> searchCandidate(int currentPage, int size, CandidateCriteriaModel candidateCriteriaModel) {
		Pageable pageable = PageRequest.of(currentPage - 1, size);

		Date dateOfBirth = new Date();
		Integer id = candidateCriteriaModel.getId();
		if (Objects.isNull(candidateCriteriaModel.getFullName()) || candidateCriteriaModel.getFullName().isBlank()
				|| candidateCriteriaModel.getFullName().isEmpty()) {
			candidateCriteriaModel.setFullName(null);
		}

		if (Objects.isNull(candidateCriteriaModel.getDateOfBirth()) || candidateCriteriaModel.getDateOfBirth().isEmpty()
				|| candidateCriteriaModel.getDateOfBirth().isBlank()) {
			dateOfBirth = null;
		} else {
			dateOfBirth = DateUtils.parseDDMMYYYYDateFromString(candidateCriteriaModel.getDateOfBirth());
		}

		if (Objects.isNull(candidateCriteriaModel.getPhone()) || candidateCriteriaModel.getPhone().isBlank()
				|| candidateCriteriaModel.getPhone().isEmpty()) {
			candidateCriteriaModel.setPhone(null);
		}

		if (Objects.isNull(candidateCriteriaModel.getEmail()) || candidateCriteriaModel.getEmail().isBlank()
				|| candidateCriteriaModel.getEmail().isEmpty()) {
			candidateCriteriaModel.setEmail(null);
		}
		if (Objects.isNull(candidateCriteriaModel.getAccount()) || candidateCriteriaModel.getAccount().isBlank()
				|| candidateCriteriaModel.getAccount().isEmpty()) {
			candidateCriteriaModel.setAccount(null);
		}

		return candidateRepository.searchCandidate(id, candidateCriteriaModel.getFullName(),candidateCriteriaModel.getAccount() , dateOfBirth,
				candidateCriteriaModel.getPhone(), candidateCriteriaModel.getEmail(), pageable);
	}

	@Override
	public Page<Candidate> findAllCandidate(int currentPage, int size) {
		Pageable pageable = PageRequest.of(currentPage - 1, size);
		return candidateRepository.findAll(pageable);
	}

	@Override
	public Candidate save(Candidate candidate) {
		return candidateRepository.save(candidate);
	}
	
	@Override
	public Candidate findById(Integer id) {
		return candidateRepository.findById(id).get();
	}

	@Override
	public void deleteCandidate(String stringCandidateId) {
		Integer candidateId = Integer.valueOf(stringCandidateId);
		candidateRepository.delete(candidateRepository.findById(candidateId).get());
		
	}

	@Override
	public Page<Candidate> searchCandidate(Integer id, String fullName, String account, Date dateOfBirth, String phone,
			String email, Pageable pageable) {
		return candidateRepository.searchCandidate(id, fullName, account, dateOfBirth, phone, email, pageable);
	}

	@Override
	public Long countByAccountContaining(String account) {
		return candidateRepository.countByAccountContaining(account);
	}

	@Override
	public List<Candidate> findAll() {
		return candidateRepository.findAll();
	}

	@Override
	public List<Candidate> searchListCandidates(CandidateCriteriaModel candidateCriteriaModel) {
		Date dateOfBirth = new Date();
		Integer id = candidateCriteriaModel.getId();
		if (Objects.isNull(candidateCriteriaModel.getFullName()) || candidateCriteriaModel.getFullName().isBlank()
				|| candidateCriteriaModel.getFullName().isEmpty()) {
			candidateCriteriaModel.setFullName(null);
		}

		if (Objects.isNull(candidateCriteriaModel.getDateOfBirth()) || candidateCriteriaModel.getDateOfBirth().isEmpty()
				|| candidateCriteriaModel.getDateOfBirth().isBlank()) {
			dateOfBirth = null;
		} else {
			dateOfBirth = DateUtils.parseDDMMYYYYDateFromString(candidateCriteriaModel.getDateOfBirth());
		}

		if (Objects.isNull(candidateCriteriaModel.getPhone()) || candidateCriteriaModel.getPhone().isBlank()
				|| candidateCriteriaModel.getPhone().isEmpty()) {
			candidateCriteriaModel.setPhone(null);
		}

		if (Objects.isNull(candidateCriteriaModel.getEmail()) || candidateCriteriaModel.getEmail().isBlank()
				|| candidateCriteriaModel.getEmail().isEmpty()) {
			candidateCriteriaModel.setEmail(null);
		}
		if (Objects.isNull(candidateCriteriaModel.getAccount()) || candidateCriteriaModel.getAccount().isBlank()
				|| candidateCriteriaModel.getAccount().isEmpty()) {
			candidateCriteriaModel.setAccount(null);
		}

		return candidateRepository.searchListCandidates(id, candidateCriteriaModel.getFullName(),candidateCriteriaModel.getAccount() , dateOfBirth,
				candidateCriteriaModel.getPhone(), candidateCriteriaModel.getEmail());
	}

	@Override
	public boolean checkIfCandidateExisted(Candidate candidate) {
		return (candidateRepository.existsByTraineeCandidateProfile_Email(candidate.getTraineeCandidateProfile().getEmail())
				|| candidateRepository
						.existsByTraineeCandidateProfile_Phone(candidate.getTraineeCandidateProfile().getPhone()));
	}
}

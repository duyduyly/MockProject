package com.example.demo.service.iservice;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Candidate;
import com.example.demo.model.CandidateCriteriaModel;

public interface ICandidateService {

	public Page<Candidate> searchCandidate(int currentPage, int size, CandidateCriteriaModel candidateCriteriaModel);
	
	public Page<Candidate> findAllCandidate(int currentPage, int size);
	
	public Page<Candidate> searchCandidate(@Param("id") Integer id, @Param("fullName") String fullName,
			@Param("account") String account, @Param("dateOfBirth") Date dateOfBirth, @Param("phone") String phone,
			@Param("email") String email, Pageable pageable);
	
	Candidate findById(Integer id);

	public Long countByAccountContaining(String account);

	public void deleteCandidate(String stringCandidateId);

	public Candidate save(Candidate candidate);

	public List<Candidate> findAll();

	public List<Candidate> searchListCandidates(CandidateCriteriaModel candidateCriteriaModel);

	public boolean checkIfCandidateExisted(Candidate candidate);
	
}

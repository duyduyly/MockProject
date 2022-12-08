package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TraineeCandidateProfile;
import com.example.demo.repository.TraineeCandidateProfileRepository;
import com.example.demo.service.iservice.ITraineeCandidateProfileService;

@Service
public class TraineeCandidateProfileService implements ITraineeCandidateProfileService {
	
	@Autowired
	private TraineeCandidateProfileRepository traineeCandidateProfileRepository;

	@Override
	public void save(TraineeCandidateProfile candidateProfile) {
		 traineeCandidateProfileRepository.save(candidateProfile);
	}

	@Override
	public List<TraineeCandidateProfile> saveAll(List<TraineeCandidateProfile> listOfTraineeCandidateProfiles) {
		return traineeCandidateProfileRepository.saveAll(listOfTraineeCandidateProfiles);
	}

	
}

package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.TraineeCandidateProfile;

public interface ITraineeCandidateProfileService {

	void save(TraineeCandidateProfile candidateProfile);

	List<TraineeCandidateProfile> saveAll(List<TraineeCandidateProfile> listOfTraineeCandidateProfiles);
}

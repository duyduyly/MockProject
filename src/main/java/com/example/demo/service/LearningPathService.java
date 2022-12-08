package com.example.demo.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.LearningPath;
import com.example.demo.repository.LearningPathRepository;
import com.example.demo.service.iservice.ILearningPathService;

@Service
public class LearningPathService implements ILearningPathService {

	@Autowired
	private LearningPathRepository learningPathRepository;

	@Override
	public List<LearningPath> findAll() {
		
		return learningPathRepository.findAll();
	}

	@Override
	public Set<String> findAllSkills() {
		return learningPathRepository.findAllSkills();
	}

	@Override
	public LearningPath save(LearningPath learningPath) {
		return learningPathRepository.save(learningPath);
	}

	@Override
	public Optional<LearningPath> findById(Integer id) {
		return learningPathRepository.findById(id);
	}
}

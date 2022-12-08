package com.example.demo.service.iservice;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.example.demo.entity.LearningPath;

public interface ILearningPathService {

	public List<LearningPath> findAll();

	public Set<String> findAllSkills();

	public LearningPath save(LearningPath learningPath);

	public Optional<LearningPath> findById(Integer id);
}

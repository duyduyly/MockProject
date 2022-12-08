package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.SubjectType;

public interface ISubjectTypeService {

	public List<SubjectType> findAll();
	
	SubjectType findBySubjectTypeName(String subjectTypeName);

	public SubjectType save(SubjectType subjectType);
}

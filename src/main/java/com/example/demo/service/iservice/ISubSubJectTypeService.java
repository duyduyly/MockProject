package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.SubSubjectType;

public interface ISubSubJectTypeService {

	List<SubSubjectType> findAll();
	
	SubSubjectType findBySubSubjectTypeName(String subSubjectTypeName);

	SubSubjectType save(SubSubjectType subSubjectType);
}

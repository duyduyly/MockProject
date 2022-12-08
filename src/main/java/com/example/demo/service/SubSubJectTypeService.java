package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SubSubjectType;
import com.example.demo.repository.SubSubjectTypeRepository;
import com.example.demo.service.iservice.ISubSubJectTypeService;

@Service
public class SubSubJectTypeService implements ISubSubJectTypeService {

	@Autowired
	private SubSubjectTypeRepository subSubjectTypeRepository;

	@Override
	public List<SubSubjectType> findAll() {
		return subSubjectTypeRepository.findAll();
	}

	@Override
	public SubSubjectType findBySubSubjectTypeName(String subSubjectTypeName) {
		return subSubjectTypeRepository.findBySubSubjectTypeName(subSubjectTypeName);
	}

	@Override
	public SubSubjectType save(SubSubjectType subSubjectType) {
		return subSubjectTypeRepository.save(subSubjectType);
	}

}

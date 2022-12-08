package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FormatType;
import com.example.demo.repository.FormatTypeRepository;
import com.example.demo.service.iservice.IFormatTypeService;

@Service
public class FormatTypeService implements IFormatTypeService {

	@Autowired
	private FormatTypeRepository formatTypeRepository;

	@Override
	public List<FormatType> findAll() {
		return formatTypeRepository.findAll();
	}

	@Override
	public FormatType findByFormatTypeName(String formatTypeName) {
		return formatTypeRepository.findByFormatTypeName(formatTypeName);
	}

	@Override
	public FormatType save(FormatType formatType) {
		return formatTypeRepository.save(formatType);
	}
}

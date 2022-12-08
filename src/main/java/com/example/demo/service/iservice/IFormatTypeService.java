package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.FormatType;

public interface IFormatTypeService {

	public List<FormatType> findAll();
	
	FormatType findByFormatTypeName(String formatTypeName);

	public FormatType save(FormatType formatType);
}

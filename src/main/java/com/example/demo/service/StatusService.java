package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Status;
import com.example.demo.repository.StatusRepository;
import com.example.demo.service.iservice.IStatusService;

@Service
public class StatusService implements IStatusService {

	@Autowired
	private StatusRepository statusRepository;
	
	@Override
	public Status findByStatusName(String string) {
		return statusRepository.findByStatusName(string);
	}
}

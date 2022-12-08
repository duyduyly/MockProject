package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FAManager;
import com.example.demo.repository.FAManagerRepository;
import com.example.demo.service.iservice.IFAManagerService;

@Service
public class FAManagerService implements IFAManagerService {
	
	@Autowired
	private FAManagerRepository faManagerRepository;

	@Override
	public Long countByAccountContaining(String account) {
		return faManagerRepository.countByAccountContaining(account);
	}

	@Override
	public Collection<FAManager> findAll() {
		return faManagerRepository.findAll();
	}
	
	@Override
	public boolean checkIfFAManagerExisted(FAManager faManager) {
		return faManagerRepository.existsByEmail(faManager.getEmail());
	}
}

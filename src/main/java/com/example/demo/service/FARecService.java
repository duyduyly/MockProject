package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.FARec;
import com.example.demo.repository.FARecRepository;
import com.example.demo.service.iservice.IFARecService;

@Service
public class FARecService implements IFARecService {
	
	@Autowired
	private FARecRepository faRecRepository;

	@Override
	public Long countByAccountContaining(String account) {
		return faRecRepository.countByAccountContaining(account);
	}

	@Override
	public Collection<FARec> findAll() {
		return faRecRepository.findAll();
	}

	@Override
	public boolean checkIfFArecExisted(FARec faRec) {
		return faRecRepository.existsByEmail(faRec.getEmail());
	}
}

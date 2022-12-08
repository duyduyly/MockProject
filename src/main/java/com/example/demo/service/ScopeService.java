package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Scope;
import com.example.demo.repository.ScopeRepository;
import com.example.demo.service.iservice.IScopeService;

@Service
public class ScopeService implements IScopeService {

	@Autowired
	private ScopeRepository scopeRepository;

	@Override
	public List<Scope> findAll() {
		return scopeRepository.findAll();
	}

	@Override
	public Scope findByScopeName(String scopeName) {
		return scopeRepository.findByScopeName(scopeName);
	}

	@Override
	public Scope save(Scope scope) {
		return scopeRepository.save(scope);
	}
}

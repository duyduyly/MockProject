package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Scope;

public interface IScopeService {

	public List<Scope> findAll();
	
	Scope findByScopeName(String scopeName);

	public Scope save(Scope scope);
}

package com.example.demo.service.iservice;

import java.util.Collection;

import com.example.demo.entity.FAManager;

public interface IFAManagerService {
	
	Long countByAccountContaining(String account);

	Collection<FAManager> findAll();

	boolean checkIfFAManagerExisted(FAManager faManager);

}

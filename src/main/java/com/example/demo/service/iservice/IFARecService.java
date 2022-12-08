package com.example.demo.service.iservice;

import java.util.Collection;

import com.example.demo.entity.FARec;

public interface IFARecService {
	
	Long countByAccountContaining(String account);

	Collection<FARec> findAll();

	boolean checkIfFArecExisted(FARec faRec);

}

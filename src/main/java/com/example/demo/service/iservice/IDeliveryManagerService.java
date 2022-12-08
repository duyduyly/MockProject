package com.example.demo.service.iservice;

import java.util.Collection;

import com.example.demo.entity.DeliveryManager;

public interface IDeliveryManagerService {
	
	Long countByAccountContaining(String account);

	Collection<DeliveryManager> findAll();

	boolean checkIfDeliManagerExisted(DeliveryManager person);

}

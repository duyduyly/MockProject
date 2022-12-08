package com.example.demo.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DeliveryManager;
import com.example.demo.repository.DeliveryManagerRepository;
import com.example.demo.service.iservice.IDeliveryManagerService;

@Service
public class DeliveryManagerService implements IDeliveryManagerService{
	
	@Autowired
	private DeliveryManagerRepository deliveryManagerRepository;

	@Override
	public Long countByAccountContaining(String account) {
		return deliveryManagerRepository.countByAccountContaining(account);
	}

	@Override
	public Collection<DeliveryManager> findAll() {
		return deliveryManagerRepository.findAll();
	}

	@Override
	public boolean checkIfDeliManagerExisted(DeliveryManager deliManager) {
		return deliveryManagerRepository.existsByEmail(deliManager.getEmail());
	}

}

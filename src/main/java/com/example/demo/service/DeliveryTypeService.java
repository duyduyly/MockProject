package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.DeliveryType;
import com.example.demo.repository.DeliveryTypeRepository;
import com.example.demo.service.iservice.IDeliveryTypeService;

@Service
public class DeliveryTypeService implements IDeliveryTypeService {

	@Autowired
	private DeliveryTypeRepository deliveryTypeRepository;
	
	@Override
	public List<DeliveryType> findAll() {

		return deliveryTypeRepository.findAll();
	}

	@Override
	public DeliveryType findByDeliveryTypeName(String deliveryTypeName) {
		return deliveryTypeRepository.findByDeliveryTypeName(deliveryTypeName);
	}

	@Override
	public DeliveryType save(DeliveryType deliveryType) {
		return deliveryTypeRepository.save(deliveryType);
	}

}

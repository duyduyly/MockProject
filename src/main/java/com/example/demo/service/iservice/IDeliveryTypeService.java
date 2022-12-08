package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.DeliveryType;

public interface IDeliveryTypeService {

	public List<DeliveryType> findAll();
	
	public DeliveryType findByDeliveryTypeName(String deliveryTypeName);

	public DeliveryType save(DeliveryType deliveryType);
	
}

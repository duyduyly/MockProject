package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.SupplierPartner;
import com.example.demo.repository.SupplierPartnerRepository;
import com.example.demo.service.iservice.ISupplierPartnerService;

@Service
public class SupplierPartnerService implements ISupplierPartnerService {

	@Autowired
	private SupplierPartnerRepository supplierPartnerRepository;

	@Override
	public List<SupplierPartner> findAll() {
		return supplierPartnerRepository.findAll();
	}

	@Override
	public SupplierPartner findBySupplierPartnerName(String supplierPartnerName) {
		return supplierPartnerRepository.findBySupplierPartnerName(supplierPartnerName);
	}
}

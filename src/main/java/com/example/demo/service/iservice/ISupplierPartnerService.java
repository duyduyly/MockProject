package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.SupplierPartner;

public interface ISupplierPartnerService {

	List<SupplierPartner> findAll();
	
	SupplierPartner findBySupplierPartnerName(String supplierPartnerName);
}

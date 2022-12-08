package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.SupplierPartner;

@Repository
public interface SupplierPartnerRepository extends JpaRepository<SupplierPartner, Integer> {
	
	SupplierPartner findBySupplierPartnerName(String supplierPartnerName);

}

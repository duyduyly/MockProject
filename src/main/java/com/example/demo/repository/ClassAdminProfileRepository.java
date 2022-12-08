package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassAdminProfile;

@Repository
public interface ClassAdminProfileRepository extends JpaRepository<ClassAdminProfile, Integer> {

	ClassAdminProfile findByFullName (String fullName);
	
	ClassAdminProfile findByClassAdmin_IdIs (Integer classAdminId);
}

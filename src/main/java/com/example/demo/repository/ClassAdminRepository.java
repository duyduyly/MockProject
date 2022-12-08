package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.ClassAdmin;

@Repository
public interface ClassAdminRepository extends JpaRepository<ClassAdmin, Integer>{
	
	ClassAdmin findByUsername (String username);

	ClassAdmin findByClassAdminProfile_FullNameIs (String fullName);
	
	Long countByAccountContaining(String account);
	
	Boolean existsByClassAdminProfile_Email(String email);
	
	Boolean existsByClassAdminProfile_Phone(String phone);
}

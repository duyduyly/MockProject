package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.ClassAdmin;

public interface IClassAdminService {

	public List<ClassAdmin> findAll();
	
	public ClassAdmin findByUsername (String username);

	public ClassAdmin findByClassAdminProfile_FullNameIs (String fullName);
	
	public Long countByAccountContaining(String account);

	boolean checkIfClassAdminExisted(ClassAdmin classAdmin);
}

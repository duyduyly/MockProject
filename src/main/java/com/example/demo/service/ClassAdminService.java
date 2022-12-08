package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.ClassAdmin;
import com.example.demo.repository.ClassAdminRepository;
import com.example.demo.service.iservice.IClassAdminService;

@Service
public class ClassAdminService implements IClassAdminService {

	@Autowired
	private ClassAdminRepository classAdminRepository;

	@Override
	public List<ClassAdmin> findAll() {

		return classAdminRepository.findAll();
	}

	@Override
	public ClassAdmin findByUsername(String username) {
		return classAdminRepository.findByUsername(username);
	}

	@Override
	public ClassAdmin findByClassAdminProfile_FullNameIs(String fullName) {
		return classAdminRepository.findByClassAdminProfile_FullNameIs(fullName);
	}

	@Override
	public Long countByAccountContaining(String account) {
		return classAdminRepository.countByAccountContaining(account);
	}

	@Override
	public boolean checkIfClassAdminExisted(ClassAdmin classAdmin) {
		return (classAdminRepository.existsByClassAdminProfile_Email(classAdmin.getClassAdminProfile().getEmail())
				|| classAdminRepository.existsByClassAdminProfile_Phone(classAdmin.getClassAdminProfile().getPhone()));
	}

}

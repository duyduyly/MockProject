package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RoleRepository;
import com.example.demo.service.iservice.IRoleService;

@Service
public class RoleService implements IRoleService{

	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public List<Role> findBySetOfUsers_Username(String username) {
		return roleRepository.findBySetOfUsers_Username(username);
	}

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role save(Role role) {
		return roleRepository.save(role);
	}
}

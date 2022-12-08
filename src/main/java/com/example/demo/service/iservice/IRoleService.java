package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Role;

public interface IRoleService {

	List<Role> findBySetOfUsers_Username(String username);

	Role findByName(String name);

	Role save(Role adminRole);

}

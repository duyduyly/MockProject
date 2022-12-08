package com.example.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
	
	List<Role> findBySetOfUsers_Username(String username);
	
	Role findByName(String name);

}

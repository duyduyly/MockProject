package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Scope;

@Repository
public interface ScopeRepository extends JpaRepository<Scope, Integer> {

	Scope findByScopeName(String scopeName);
}

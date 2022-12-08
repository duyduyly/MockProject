package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Budget;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Integer>{
	
	Budget findByBudgetName(String budgetName);
}

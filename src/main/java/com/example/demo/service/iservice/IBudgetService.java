package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Budget;

public interface IBudgetService {

	public List<Budget> findAll();

	Budget findByBudgetName(String budgetName);

	public Budget save(Budget budget);
}

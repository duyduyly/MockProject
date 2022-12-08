package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Budget;
import com.example.demo.repository.BudgetRepository;
import com.example.demo.service.iservice.IBudgetService;

@Service
public class BudgetService implements IBudgetService {

	@Autowired
	private BudgetRepository budgetRepository;

	@Override
	public List<Budget> findAll() {

		return budgetRepository.findAll();
	}

	@Override
	public Budget findByBudgetName(String budgetName) {
		return budgetRepository.findByBudgetName(budgetName);
	}

	@Override
	public Budget save(Budget budget) {
		return budgetRepository.save(budget);
	}

}

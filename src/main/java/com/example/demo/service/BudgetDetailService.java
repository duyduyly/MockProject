package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.BudgetDetail;
import com.example.demo.repository.BudgetDetailRepository;
import com.example.demo.service.iservice.IBudgetDetailService;

@Service
public class BudgetDetailService implements IBudgetDetailService {

	@Autowired
	BudgetDetailRepository budgetDetailRepository;
	
	@Override
	public BudgetDetail save(BudgetDetail budgetDetail) {

		return budgetDetailRepository.save(budgetDetail);
		
	}

	@Override
	public BudgetDetail findById(Integer id) {
		// TODO Auto-generated method stub
		return budgetDetailRepository.findById(id).get();
	}

	@Override
	public void delete(BudgetDetail budgetDetail) {
		budgetDetailRepository.delete(budgetDetail);
		
	}

}

package com.example.demo.service.iservice;

import com.example.demo.entity.BudgetDetail;

public interface IBudgetDetailService {

	BudgetDetail save(BudgetDetail budgetDetail) ;
	
	BudgetDetail findById(Integer id) ;
	
	void delete(BudgetDetail budgetDetail);
}

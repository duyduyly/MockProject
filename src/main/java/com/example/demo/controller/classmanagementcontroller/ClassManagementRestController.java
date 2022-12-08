package com.example.demo.controller.classmanagementcontroller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.BudgetDetail;
import com.example.demo.service.iservice.IClassBatchService;

@RestController
@RequestMapping("/classBatch")
public class ClassManagementRestController {
	@Autowired
	HttpSession httpSession;

	@Autowired
	private IClassBatchService classBatchService;
	
	@PostMapping(value = "/getSetBudGetDetail")
	public ResponseEntity<?> getSetBudgetDetail(@RequestBody() Set<BudgetDetail> budgetDetails) {

		httpSession.setAttribute("budgetDetails", budgetDetails);

		budgetDetails.forEach(item -> {
			System.err.println(item.toString());
		});
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/getSetBudGetDetailToForm")
	public Set<BudgetDetail> getBudgetDetailinViews() {
		System.err.println("Id: "+httpSession.getAttribute("classBatchIdUpdate"));
		
		String str = httpSession.getAttribute("classBatchIdUpdate").toString();
		Integer classBatchId = Integer.parseInt(str);

		Set<BudgetDetail> budgetDetailSet = new HashSet<BudgetDetail>() ;
		
		
		for (BudgetDetail budgetDetail : classBatchService.findById(classBatchId).get().getSetOfBudgetDetails()) {
			budgetDetailSet.add(budgetDetail);
		}
		System.err.println(budgetDetailSet.toString());
		
		return budgetDetailSet;
	}
	
	@PostMapping(value = "/postSetBudGetDetailToForm")
	public ResponseEntity<?> setBudgetDetailinViews(@RequestBody() Set<BudgetDetail> budgetDetails) {
		System.err.println("http Post Budget Detail: "+budgetDetails.toString());
		
		httpSession.setAttribute("budgetDetailsUpdate", budgetDetails);
		return ResponseEntity.ok(HttpStatus.OK);
	}
	
	
}

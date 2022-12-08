package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Audit;
import com.example.demo.repository.AuditRepository;
import com.example.demo.service.iservice.IAuditService;

@Service
public class AuditService implements IAuditService{
	
	@Autowired
	private AuditRepository auditRepository;

	@Override
	public Audit findByClassBatch_IdIs(Integer classBatchId) {
		return auditRepository.findByClassBatch_IdIs(classBatchId);
	}

	@Override
	public void save(Audit audit) {
		auditRepository.save(audit);
		
	}

}

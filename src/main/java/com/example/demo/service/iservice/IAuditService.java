package com.example.demo.service.iservice;

import com.example.demo.entity.Audit;

public interface IAuditService {
	
	Audit findByClassBatch_IdIs (Integer classBatchId);
	
	void save(Audit audit);
}

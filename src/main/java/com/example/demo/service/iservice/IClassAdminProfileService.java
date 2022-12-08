package com.example.demo.service.iservice;

import com.example.demo.entity.ClassAdminProfile;

public interface IClassAdminProfileService {
	
	public ClassAdminProfile findByFullName (String fullName);
	
	public ClassAdminProfile findByClassAdmin_IdIs (Integer classAdminId);

	public ClassAdminProfile save(ClassAdminProfile classAdminProfile);

}

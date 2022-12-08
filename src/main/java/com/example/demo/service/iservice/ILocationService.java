package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Location;

public interface ILocationService {

	public List<Location> findAll();
	
	Location findByLocationName(String locationName);

	public Location save(Location location);
	
}

package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Location;
import com.example.demo.repository.LocationRepository;
import com.example.demo.service.iservice.ILocationService;

@Service
public class LocationService implements ILocationService {

	@Autowired
	private LocationRepository locationRepository;

	@Override
	public List<Location> findAll() {
		return locationRepository.findAll();
	}

	@Override
	public Location findByLocationName(String locationName) {
		return locationRepository.findByLocationName(locationName);
	}

	@Override
	public Location save(Location location) {
		return locationRepository.save(location);
	}
}

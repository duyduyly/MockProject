package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Channel;
import com.example.demo.repository.ChannelRepository;
import com.example.demo.service.iservice.IChannelService;

/**
 * @author HuyNQ
 *
 */
@Service
public class ChannelService implements IChannelService {
	
	@Autowired
	ChannelRepository channelRepository;
	
	/**
	 * using java 8 to convert Iterable to list
	 * @return channel list
	 */
	@Override
	public List<Channel> getAllChannels() {
		
		return channelRepository.findAll();
	}
	
	/**
	 * check channel and save into database
	 * @param c: channel
	 * @return boolean
	 */
	@Override
	public boolean save(Channel c) {
		if (Objects.isNull(c)) {
			System.out.println("Channel is null in save operation!");
			return false;
		}
		try {
			channelRepository.save(c);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
			return false;
		}
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Channel findByChannelName(String channelName) {
		Optional<Channel> opt = channelRepository.findByChannelName(channelName);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			System.out.println("Channel is null in findByChannelName function.");
			return null;
		}
	}
}

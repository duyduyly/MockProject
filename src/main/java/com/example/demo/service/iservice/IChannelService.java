package com.example.demo.service.iservice;

import java.util.List;

import com.example.demo.entity.Channel;

/**
 * @author HuyNQ
 *
 */
public interface IChannelService {
	
	public Channel findByChannelName(String channelName);

	public boolean save(Channel c);
	
	public boolean delete();
	
	public List<Channel> getAllChannels();
}

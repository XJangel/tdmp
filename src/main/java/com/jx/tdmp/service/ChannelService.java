package com.jx.tdmp.service;

import com.jx.tdmp.entity.Channel;
import com.jx.tdmp.entity.Network;

import java.util.List;

public interface ChannelService {
    List<Channel> listChannels();
    Channel getChannelById(String id);
    Channel createChannel(Channel Channel);
//    Channel updateChannel(Channel Channel);
//    boolean deleteChannel(String id);
}

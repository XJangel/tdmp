package com.jx.tdmp.controller;

import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.entity.Channel;
import com.jx.tdmp.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    @Resource(name = "channelServiceImpl")
    private ChannelService channelService;

    @RequestMapping
    public ResponseResult<Channel> listChannels(){
        return null;
    }

    @PostMapping
    public ResponseResult<Channel> createChannel(){
        return null;
    }

    @PostMapping
    public ResponseResult<Channel> joinChannel(){
        return null;
    }

}

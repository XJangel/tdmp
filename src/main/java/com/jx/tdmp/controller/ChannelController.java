package com.jx.tdmp.controller;

import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.entity.IntermediateChannel;
import com.jx.tdmp.service.Interface.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/channels")
public class ChannelController {
    @Autowired
    @Resource(name = "channelServiceImpl")
    private ChannelService channelService;

    @RequestMapping
    @ResponseBody
    public ResponseResult<IntermediateChannel> listChannels() {
        return null;
    }

    @PostMapping
    @ResponseBody
    public ResponseResult<IntermediateChannel> createChannel() {
        return null;
    }

    /*
    genesis block->
    channel configuration:
    - organizations
    - channel access policies
    - block batch size
    - batch timeout
    - channel restrictions
    - channel creation policy

     */
    @PostMapping
    @ResponseBody
    public ResponseResult<IntermediateChannel> updateChannel() {
        return null;
    }

    @PostMapping(value = "/{channel_id}/peers")
    @ResponseBody
    public ResponseResult<IntermediateChannel> joinChannel(@RequestBody List<String> peers) {
        return null;
    }

    @RequestMapping(value = "/{channel_id}/peers")
    @ResponseBody
    public ResponseResult<Object> listChannelPeers(@PathVariable("channel_id") String channel_id) {
        return null;
    }

}

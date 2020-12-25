package com.jx.tdmp.controller;


import com.jx.tdmp.common.lang.ResponseResult;
import com.jx.tdmp.entity.Network;
import com.jx.tdmp.service.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
@RestController
@RequestMapping("/network")
public class NetworkController {

    @Autowired
    @Resource(name = "networkServiceImpl")
    private NetworkService networkService;

    @RequestMapping
    @ResponseBody
    public ResponseResult<List<Network>> listNetworks() {
        return null;
    }

    @PostMapping
    @ResponseBody
    public ResponseResult<Network> createNetwork(@RequestBody @Validated Network network) {

        return null;
    }

    @PutMapping(value = "/{id}")
    @ResponseBody
    public ResponseResult<Network> updateNetwork(@RequestBody @Validated @PathVariable("id") Network network) {

        return null;
    }

    @DeleteMapping(value = "/{id}")
    @ResponseBody
    public ResponseResult<Network> deleteNetwork(@RequestBody @Validated @PathVariable("id") String id) {

        return null;
    }

}

package com.jx.tdmp.service;

import com.jx.tdmp.entity.Network;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jx.tdmp.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
public interface NetworkService extends IService<Network> {

    List<Network> listNetworks();
    Network getNetworkrById(String id);
    Network createNetwork(Network network);
    Network updateNetwork(Network network);
    boolean deleteNetwork(String id);

}

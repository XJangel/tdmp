package com.jx.tdmp.service.Interface;

import com.jx.tdmp.entity.Network;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jx.tdmp.entity.User;

import java.util.List;

/**
 * <p>
 *  NetworkService接口
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
public interface NetworkService extends IService<Network> {

    List<Network> listNetworks();
    Network getNetworkById(String id);
    Network createNetwork(Network network);
    Network updateNetwork(Network network);
    boolean deleteNetwork(String id);

}

package com.jx.tdmp.service.impl;

import com.jx.tdmp.entity.Network;
import com.jx.tdmp.mapper.NetworkMapper;
import com.jx.tdmp.service.NetworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
@Service
public class NetworkServiceImpl extends ServiceImpl<NetworkMapper, Network> implements NetworkService {

    @Override
    public List<Network> listNetworks() {
        return null;
    }

    @Override
    public Network getNetworkrById(String id) {
        return null;
    }

    @Override
    public Network createNetwork(Network network) {
        return null;
    }

    @Override
    public Network updateNetwork(Network network) {
        return null;
    }

    @Override
    public boolean deleteNetwork(String id) {
        return false;
    }
}

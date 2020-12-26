package com.jx.tdmp.service.Impl.fabric;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jx.tdmp.entity.Network;
import com.jx.tdmp.entity.User;
import com.jx.tdmp.mapper.NetworkMapper;
import com.jx.tdmp.service.Impl.k8s.NormalResourcesOp;
import com.jx.tdmp.service.NetworkService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 * NetworkService实现类
 * </p>
 *
 * @author Jiejie Xu
 * @since 2020-12-25
 */
@Slf4j
@Service
public class NetworkServiceImpl extends ServiceImpl<NetworkMapper, Network> implements NetworkService {

    @Autowired
    private NetworkMapper networkMapper;
    @Autowired
    private NormalResourcesOp normalResourcesOp;

    @Override
    public List<Network> listNetworks() {
        return null;
    }

    @Override
    public Network getNetworkById(String id) {
        QueryWrapper<Network> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id").gt("id", id);
        return networkMapper.selectById(queryWrapper);
    }

    // 创建network只是创建相应的peer order，并不涉及用户channel的创建以及链码的一系列操作
    @Override
    public Network createNetwork(Network network) {
        // ①创建相应的namespace
        try {
            normalResourcesOp.createNamespaceByName("");

        }catch (Exception e){
            log.error("failed to create blockchain network:"+network.getName());
        }
        finally {
//            return null;
        }

        // ②根据相应的配置创建相应的peer orderer
        // 调用apilcient创建相应的configmap以及deployment以及service
        // 具体yaml信息见k8sResourcesForFabric目录
        // 注意对于k8s不同的版本也有不同的区别对待1.11 区别在于默认的运行时是docker还是containerd，猜测是因为安装链码需要docker


        return null;
    }

    // update blockchain network，比如增加/减少peer order
    // 是否支持再增加组织？按理说应该是不支持的
    @Override
    public Network updateNetwork(Network network) {
        return null;
    }

    // delete network.更新数据库中network表的release_ts字段
    @Override
    public boolean deleteNetwork(String id) {
        return false;
    }
}

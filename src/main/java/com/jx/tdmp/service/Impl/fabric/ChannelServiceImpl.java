package com.jx.tdmp.service.Impl.fabric;

import com.jx.tdmp.entity.IntermediateChannel;
import com.jx.tdmp.service.Interface.ChannelService;
import org.hyperledger.fabric.sdk.Channel;
import org.hyperledger.fabric.sdk.ChannelConfiguration;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    private HFClientCreateService HFClientCreateService;

    // 直接HFClient作为属性，下面就很容易进行操作

    @Override
    public List<IntermediateChannel> listChannels() {
        // 根据当前user所属Org列出org中peer所在的所有channel
        return null;
    }

    // 根据channel ID列出channel详细信息
    @Override
    public IntermediateChannel getChannelById(String id) throws InstantiationException, InvocationTargetException, NoSuchMethodException, MalformedURLException, IllegalAccessException, InvalidArgumentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, EnrollmentException, CryptoException, ClassNotFoundException {
        // genj
        Channel channel = HFClientCreateService.createFabricUser().getChannel(id);
//        HFClientCreateService.createFabricUser().queryChannels();
        return null;
    }

    //
    @Override
    public IntermediateChannel createChannel(IntermediateChannel IntermediateChannel) throws InvalidArgumentException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException, IllegalAccessException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, EnrollmentException, CryptoException, ClassNotFoundException, TransactionException {
        HFClient hfClient = HFClientCreateService.createFabricUser();
//        hfClient.queryChannels();
//        hfClient.queryInstalledChaincodes();

        ChannelConfiguration channelConfiguration = new ChannelConfiguration(new File(""));
        Channel channel = hfClient.newChannel("name", null, channelConfiguration, hfClient.getChannelConfigurationSignature(channelConfiguration, hfClient.getUserContext()));
        channel.initialize();
//        hfClient.newOrderer()
        return null;
    }
//    public Orderer getOrderer() throws Exception {
//        if (orderer == null) {
//            String path = ClasspathFileUtils.getFileFromSpringBootClassPath(ordererProperties.getTlscaCert()).getPath();
//            Properties properties = new Properties();
//            properties.setProperty("pemFile", path);
//            Orderer orderer = hfClient.newOrderer(ordererProperties.getName(), ordererProperties.getGrpcsAddr(), properties);
//            return orderer;
//        }
//        return orderer;
//    }
//
//    /**
//     * 获取peer对象
//     *
//     * @return
//     * @throws Exception
//     */
//    public Peer getOrg1Peer0() throws Exception {
//        if (org1Peer0 == null) {
//            String path = ClasspathFileUtils.getFileFromSpringBootClassPath(org1Peer0Properties.getTlscaCert()).getPath();
//            Properties properties = new Properties();
//            properties.setProperty("pemFile", path);
//            Peer peer = hfClient.newPeer(org1Peer0Properties.getName(), org1Peer0Properties.getGrpcsAddr(), properties);
//            return peer;
//        }
//        return org1Peer0;
//    }
}

package com.jx.tdmp.service.Interface;

import com.jx.tdmp.entity.IntermediateChannel;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.exception.TransactionException;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.util.List;

public interface ChannelService {
    List<IntermediateChannel> listChannels();
    IntermediateChannel getChannelById(String id) throws InstantiationException, InvocationTargetException, NoSuchMethodException, MalformedURLException, IllegalAccessException, InvalidArgumentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, EnrollmentException, CryptoException, ClassNotFoundException;
    IntermediateChannel createChannel(IntermediateChannel IntermediateChannel) throws InvalidArgumentException, InstantiationException, InvocationTargetException, NoSuchMethodException, IOException, IllegalAccessException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException, EnrollmentException, CryptoException, ClassNotFoundException, TransactionException;
//    Channel updateChannel(Channel Channel);
//    boolean deleteChannel(String id);
}

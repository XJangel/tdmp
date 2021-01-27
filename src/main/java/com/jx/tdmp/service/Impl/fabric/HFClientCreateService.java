package com.jx.tdmp.service.Impl.fabric;

import com.jx.tdmp.entity.FabricUser;
import org.hyperledger.fabric.sdk.Enrollment;
import org.hyperledger.fabric.sdk.HFClient;
import org.hyperledger.fabric.sdk.exception.CryptoException;
import org.hyperledger.fabric.sdk.exception.InvalidArgumentException;
import org.hyperledger.fabric.sdk.security.CryptoSuite;
import org.hyperledger.fabric_ca.sdk.HFCAClient;
import org.hyperledger.fabric_ca.sdk.exception.EnrollmentException;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;

@Service
public class HFClientCreateService {

    public HFClient createFabricUser() throws IllegalAccessException, InvocationTargetException, InvalidArgumentException, InstantiationException, NoSuchMethodException, CryptoException, ClassNotFoundException, MalformedURLException, EnrollmentException, org.hyperledger.fabric_ca.sdk.exception.InvalidArgumentException {
        CryptoSuite cryptoSuite = CryptoSuite.Factory.getCryptoSuite();
        // 实例化fabric-ca服务器
        HFCAClient hfcaClient = HFCAClient.createNewInstance("grpc://localhost:7054", null);
        hfcaClient.setCryptoSuite(cryptoSuite);
        // ca-client登录admin用户才有权进行user的注册
        Enrollment adminEnrollment = hfcaClient.enroll("admin", "adminpw");
        FabricUser fabricUser = new FabricUser("admin", "org1MSP", adminEnrollment);

        // 创建fabric client
        HFClient client = HFClient.createNewInstance();
        client.setCryptoSuite(CryptoSuite.Factory.getCryptoSuite());
        client.setUserContext(fabricUser);
        return null;
    }
}

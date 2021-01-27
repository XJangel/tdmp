package com.jx.tdmp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/暂时未知")
public class PeerController {

    // 创建peer：①向fabric-ca（TLS+EC）分别注册相应用户，并通过HFCAclient登录相应用户，完成证书的下发，另外要登录对应组织的admin用户，
    // 拿到admin用户的msp证书，安装和实例化链码需要（注意要把admin用户的证书放在指定位置：../org1/peer1/msp/admincerts/org1-admin-cert.pem）

    // peer加入channel

    //
}

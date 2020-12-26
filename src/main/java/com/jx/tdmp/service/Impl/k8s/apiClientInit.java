package com.jx.tdmp.service.Impl.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileReader;
import java.io.IOException;

@Service
public class ApiClientInit {

    // TODO:从前端获取的kubeconfig位置
    // file path to your KubeConfig
    @Value("")
    private String kubeconfigPath;

    public ApiClientInit(String kubeconfigPath) {
        this.kubeconfigPath = kubeconfigPath;
    }

    public ApiClientInit() {
    }

    // 创建k8sclient的过程包括两种方式：①根据kubeconfig创建  ②创建内部client
    public ApiClient generateK8sClient() throws IOException {
        ApiClient apiClient;
        if (!kubeconfigPath.equals("")) {
            // loading the out-of-cluster config, a kubeconfig from file-system
            apiClient =
                    ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeconfigPath))).build();
        } else {
            // loading the in-cluster config, including:
            //   1. service-account CA
            //   2. service-account bearer-token
            //   3. service-account namespace
            //   4. master endpoints(ip, port) from pre-set environment variables
            apiClient = ClientBuilder.cluster().build();
        }
        Configuration.setDefaultApiClient(apiClient);
        return apiClient;
    }

    public String getKubeconfigPath() {
        return kubeconfigPath;
    }

    public void setKubeconfigPath(String kubeconfigPath) {
        this.kubeconfigPath = kubeconfigPath;
    }
}

package com.jx.tdmp.service.Impl.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.KubeConfig;
import org.springframework.beans.factory.annotation.Value;

import java.io.FileReader;
import java.io.IOException;

public class apiClientInit {

    // TODO:从前端获取的kubeconfig位置
    @Value("${kubeconfigPath:}")
    private String kubeconfigPath;

    public apiClientInit(String kubeconfigPath) {
        this.kubeconfigPath = kubeconfigPath;
    }

    public apiClientInit() {
    }

    // 创建k8sclient的过程包括两种方式：①根据kubeconfig创建②创建内部client
    public ApiClient generateK8sClient() throws IOException {
        ApiClient apiClient;
        if (!kubeconfigPath.equals("")) {
            apiClient =
                    ClientBuilder.kubeconfig(KubeConfig.loadKubeConfig(new FileReader(kubeconfigPath))).build();
        } else {
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

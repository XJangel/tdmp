package com.jx.tdmp.service.Impl.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

// 对k8s内置资源对象的增删改查
/*
ns
deployment
job
deamonset
service
 */

@Slf4j
public class normalResourcesOp {
    private CoreV1Api apiInstance;

    public normalResourcesOp() {
        this.apiInstance = new CoreV1Api();
    }

    public Map<String, String> creatNamespace(V1Namespace body) throws IOException {
        Map<String, String> message = new HashMap<>();
        // k8s初始化
        ApiClient client = new apiClientInit("").generateK8sClient();
//        CoreV1Api apiInstance = new CoreV1Api();
        try {
            V1Namespace result = apiInstance.createNamespace(body, null, null, null);
            message.put("success", "应用命名空间创建成功！");
        } catch (ApiException e) {
            log.error("Exception when calling CoreV1Api#createNamespace");
            log.error("Status code: {}", e.getCode());
            log.error("Reason: {}", e.getResponseBody());
            log.error("Response headers: {}", e.getResponseHeaders());
            if (e.getCode() == 409) {
                message.put("error", "命名空间已重复！");
            }
            if (e.getCode() == 200) {
                message.put("success", "应用命名空间创建成功！");
            }
            if (e.getCode() == 201) {
                message.put("error", "命名空间已重复！");
            }
            if (e.getCode() == 401) {
                message.put("error", "无权限操作！");
            }
            message.put("error", "应用命名空间创建失败！");
        }
        return message;
    }
}

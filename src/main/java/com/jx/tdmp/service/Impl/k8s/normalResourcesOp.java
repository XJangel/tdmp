package com.jx.tdmp.service.Impl.k8s;

import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Namespace;
import io.kubernetes.client.openapi.models.V1NamespaceList;
import io.kubernetes.client.openapi.models.V1ObjectMeta;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.generic.GenericKubernetesApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class NormalResourcesOp {
    @Autowired
    private ApiClientInit apiClientInit;

    private CoreV1Api apiInstance;

    public NormalResourcesOp() {
        this.apiInstance = new CoreV1Api();
    }

    public boolean createNamespaceByName(String name) throws IOException, ApiException {
        Map<String, String> hm = new HashMap<>();
        hm.put("name", name);
        V1Namespace ns = new V1Namespace().
                metadata(new V1ObjectMeta().name(name).labels(hm));
        creatNamespace(ns);
        return true;
    }

    public Map<String, String> creatNamespace(V1Namespace ns) throws IOException, ApiException {
        ApiClient apiClient = apiClientInit.generateK8sClient();
        GenericKubernetesApi<V1Namespace, V1NamespaceList> nsClient =
                new GenericKubernetesApi<>(V1Namespace.class, V1NamespaceList.class, "", "v1", "Namespaces", apiClient);

        V1Namespace latestNs = nsClient.create(ns).throwsApiException().getObject();
        return null;


//        Map<String, String> message = new HashMap<>();
//        // k8s初始化
//        ApiClient client = new ApiClientInit("").generateK8sClient();
////        CoreV1Api apiInstance = new CoreV1Api();
//        try {
//            V1Namespace result = apiInstance.createNamespace(body, null, null, null);
//            message.put("success", "应用命名空间创建成功！");
//        } catch (ApiException e) {
//            log.error("Exception when calling CoreV1Api#createNamespace");
//            log.error("Status code: {}", e.getCode());
//            log.error("Reason: {}", e.getResponseBody());
//            log.error("Response headers: {}", e.getResponseHeaders());
//            if (e.getCode() == 409) {
//                message.put("error", "命名空间已重复！");
//            }
//            if (e.getCode() == 200) {
//                message.put("success", "应用命名空间创建成功！");
//            }
//            if (e.getCode() == 201) {
//                message.put("error", "命名空间已重复！");
//            }
//            if (e.getCode() == 401) {
//                message.put("error", "无权限操作！");
//            }
//            message.put("error", "应用命名空间创建失败！");
//        }
//        return message;
    }
}

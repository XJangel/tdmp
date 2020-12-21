package com.jx.tdmp.service.Impl.k8s;

import io.kubernetes.client.openapi.apis.CustomObjectsApi;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class customResourcesOp {

    private CustomObjectsApi apiInstance;

    public customResourcesOp() {
        apiInstance = new CustomObjectsApi();
    }


}

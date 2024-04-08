package com.pj.system.kube;

import io.kubernetes.client.Metrics;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.AppsV1Api;
import io.kubernetes.client.openapi.apis.AutoscalingV2Api;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.V1Pod;
import io.kubernetes.client.openapi.models.V1PodList;
import io.kubernetes.client.util.Config;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * @version 1.0
 * @description KubernetesClientAPI
 * @date 2024/4/8 17:26:45
 */
@Data
@Component
@Slf4j
public class KubernetesClientAPI {
    private CoreV1Api v1ApiInstance;

    private AutoscalingV2Api autoscalingV2ApiInstance;

    private AppsV1Api appsV1Api;

    private Metrics metrics;

    private ApiClient client;

    @Autowired
    private KubeBaseDTO kubeBaseDTO;

    @PostConstruct
    public void initApi() {
        this.client = Config.fromToken(kubeBaseDTO.getEndpoint(), kubeBaseDTO.getToken(), false);

        Configuration.setDefaultApiClient(client);

        v1ApiInstance = new CoreV1Api(client);
        autoscalingV2ApiInstance = new AutoscalingV2Api(client);
        appsV1Api = new AppsV1Api(client);
        metrics = new Metrics(client);
    }

    public V1PodList getPodNameList() throws ApiException {
        return this.v1ApiInstance.listPodForAllNamespaces().execute();
    }


}

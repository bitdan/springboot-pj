package com.pj.system.controller;

import cn.dev33.satoken.util.SaResult;
import com.pj.system.kube.KubernetesClientAPI;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.models.V1PodList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @description K8sController
 * @date 2024/4/8 17:24:53
 */
@RequiredArgsConstructor
@RestController("/k8s")
public class K8sController {

    private final KubernetesClientAPI kubernetesClientAPI;

    @PostMapping("/getPodNameList")
    public SaResult getPodNameList() throws ApiException {
        V1PodList podNameList = kubernetesClientAPI.getPodNameList();
        return SaResult.data(podNameList);
    }
}

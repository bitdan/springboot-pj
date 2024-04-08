package com.pj.system.kube;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 描述：
 * <p>
 *
 * @author Tangjq
 * @version 1.0
 * @created 2022-01-18 11:54
 */
@Data
@Component
@ConfigurationProperties(prefix = "k8s")
public class KubeBaseDTO implements Serializable {

    private String token;
    private String namespace;
    private String endpoint;
    private String regionId;

}

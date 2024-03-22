import io.kubernetes.client.custom.Quantity;
import io.kubernetes.client.custom.V1Patch;
import io.kubernetes.client.openapi.ApiClient;
import io.kubernetes.client.openapi.ApiException;
import io.kubernetes.client.openapi.Configuration;
import io.kubernetes.client.openapi.apis.CoreV1Api;
import io.kubernetes.client.openapi.models.*;
import io.kubernetes.client.util.ClientBuilder;
import io.kubernetes.client.util.Config;
import io.kubernetes.client.util.generic.GenericKubernetesApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

@Slf4j
public class Kubernetes {

    private String endpoint = "https://1.12.245.89:6443";
    String token = "eyJhbGciOiJSUzI1NiIsImtpZCI6Im9iWWlkT3lKTE5GSmlTc01DUGlVRi1yN1lpVUFtdVlPckpiS3dJVVQwVUkifQ.eyJhdWQiOlsiaHR0cHM6Ly9rdWJlcm5ldGVzLmRlZmF1bHQuc3ZjLmNsdXN0ZXIubG9jYWwiLCJrM3MiXSwiZXhwIjoxNzEwNTY5MjA0LCJpYXQiOjE3MTA1NjU2MDQsImlzcyI6Imh0dHBzOi8va3ViZXJuZXRlcy5kZWZhdWx0LnN2Yy5jbHVzdGVyLmxvY2FsIiwia3ViZXJuZXRlcy5pbyI6eyJuYW1lc3BhY2UiOiJrdWJlLXN5c3RlbSIsInNlcnZpY2VhY2NvdW50Ijp7Im5hbWUiOiJhZG1pbi11c2VyIiwidWlkIjoiODFkYjEyZDYtZWU3Yi00YjJjLTk1ZWUtMmU0ZjBiY2FjOGM4In19LCJuYmYiOjE3MTA1NjU2MDQsInN1YiI6InN5c3RlbTpzZXJ2aWNlYWNjb3VudDprdWJlLXN5c3RlbTphZG1pbi11c2VyIn0.lPPjRIcOUmMTrouRaWzeQ18JWoRxqzS6L2qQbP5zNEYOeKsUqq3rcdYS24Fa5EDaYjcQFFYkYFGSN0HMLZDTtogC9XRMKV_qa-Q20VcdjBUr-BQf6ey4OyfVCaAYlNaGw4u_ZYZNDCggx1dxV1Do-jKeI2QtvJXpF1ZhIKWMbwrn6StKNcbk6mEghT71yHHdiRM7gFSX65YoS4thjbMEpXfuxfrAXFyKEd4pDB2yO_LQ8gLws9E5v8lOBTZY1xjTNry35Ar3YWm8AUvsmFKcvRxc5axdtf86cfYYkJKpICc3NpWd8ynn_NQKAVxSJ2Hh6FfvizDGkIuwRWa4AdzR8w";


    @Test
    public void test1() throws IOException, ApiException {

        ApiClient client = Config.fromToken(endpoint, token, false);

        Configuration.setDefaultApiClient(client);

        CoreV1Api coreV1Api = new CoreV1Api(client);
        V1NamespaceList execute = coreV1Api.listNamespace().execute();
        for (V1Namespace item : execute.getItems()) {
            log.info("item is : {}", item);
        }
    }

    @Test
    public void test2() throws IOException, ApiException {
        // The following codes demonstrates using generic client to manipulate pods
        V1Pod pod =
                new V1Pod()
                        .metadata(new V1ObjectMeta().name("foo").namespace("default"))
                        .spec(
                                new V1PodSpec()
                                        .containers(Arrays.asList(new V1Container().name("c").image("test"))));

//        ApiClient apiClient = ClientBuilder.standard().build();

        ApiClient apiClient = Config.fromToken(endpoint, token, false);

        GenericKubernetesApi<V1Pod, V1PodList> podClient =
                new GenericKubernetesApi<>(V1Pod.class, V1PodList.class, "", "v1", "pods", apiClient);

        V1Pod latestPod = podClient.create(pod).throwsApiException().getObject();
        log.info("latestPod is : {}", latestPod);
        log.info("Created!");

//        V1Pod patchedPod =
//                podClient
//                        .patch(
//                                "default",
//                                "foo",
//                                V1Patch.PATCH_FORMAT_STRATEGIC_MERGE_PATCH,
//                                new V1Patch("{\"metadata\":{\"finalizers\":[\"example.io/foo\"]}}"))
//                        .throwsApiException()
//                        .getObject();
//        log.info("Patched!");
//
//        V1Pod deletedPod = podClient.delete("default", "foo").throwsApiException().getObject();
//        if (deletedPod != null) {
//            log.info(
//                    "Received after-deletion status of the requested object, will be deleting in background!");
//        }
//        log.info("Deleted!");
    }

    @Test
    public void test3(){

        ApiClient apiClient = Config.fromToken(endpoint, token, false);
        Configuration.setDefaultApiClient(apiClient);
        CoreV1Api api = new CoreV1Api(apiClient);
        V1Pod pod = new V1Pod();
        pod.setApiVersion("v1");
        pod.setKind("Pod");

        V1ObjectMeta metadata = new V1ObjectMeta();
        metadata.setName("example-pod");
        pod.setMetadata(metadata);

        V1Container container = new V1Container();
        container.setName("example-container");
        container.setImage("nginx:latest");
        container.setPorts(
                Arrays.asList(new V1ContainerPort().containerPort(80)));

        V1ResourceRequirements resReq = new V1ResourceRequirements();
        resReq.setLimits(Collections.singletonMap("cpu", Quantity.fromString("1")));
        container.setResources(resReq);

        V1PodSpec spec = new V1PodSpec();
        spec.setContainers(Arrays.asList(container));

        V1PodTemplateSpec template = new V1PodTemplateSpec();
        template.setSpec(spec);

        pod.setSpec(spec);

        api.createNamespacedPod("default", pod);
    }
}

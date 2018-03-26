package com.example.gatway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/users")
    public String getUsernames() {
        RestTemplate restTemplate =
                new RestTemplate();

        ServiceInstance serviceInstance = discoveryClient
                .getInstances("account-service-production")
                .get(0);

        String accountUrl = serviceInstance.getHost() + ":" + serviceInstance.getPort();

        return restTemplate.exchange(
                String.format("http://%s/users", accountUrl),
                HttpMethod.GET,
                null,
                String.class)
                .getBody();
    }
}

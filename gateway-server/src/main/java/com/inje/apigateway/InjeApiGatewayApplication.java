package com.inje.apigateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class InjeApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(InjeApiGatewayApplication.class, args);
    }

}

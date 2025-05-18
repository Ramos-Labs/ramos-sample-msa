package com.inje.injediscoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class InjeDiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InjeDiscoveryServerApplication.class, args);
    }

}

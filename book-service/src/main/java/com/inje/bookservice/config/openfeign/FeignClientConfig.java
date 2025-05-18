package com.inje.bookservice.config.openfeign;

import feign.Logger;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FeignClient Configuration Bean.
 *
 * @author HakHyeon Song
 */
@EnableFeignClients(basePackages = "com.inje.bookservice")
@Configuration
public class FeignClientConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}

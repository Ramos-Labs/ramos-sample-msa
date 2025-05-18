package com.inje.apigateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Gateway 전역에서 사용되는 Tracing Logging Filter.
 *
 * @author HakHyeon Song
 */
@Slf4j
@Component
public class GlobalLoggingFilter extends AbstractGatewayFilterFactory<GlobalLoggingFilter.Config> {

    public GlobalLoggingFilter() {
        super(Config.class);
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }

    /**
     * Gateway로 들어오는 모든 요청에 대해 tracing log를 남기기 위한 Filter 로직.
     *
     * @param config filter의 설정 클래스
     */
    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();

            log.info("Global Logging Filter baseMessage: {}", config.getBaseMessage());
            if (config.preLogger) {
                log.info("Global Logging Filter Start : request id -> {}", request.getId());
                log.info("Global Logging Filter Start : request uri -> {}", request.getURI());
                log.info("Global Logging Filter Start : request path -> {}", request.getPath());
            }

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                if (config.postLogger) {
                    log.info("Global Logging Filter End : response code -> {}", response.getStatusCode());
                }
            }));
        };
    }
}

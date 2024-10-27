package vin.vio.gatewayproxydemo.conf;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;

import reactor.core.publisher.Mono;

import java.util.UUID;

@Component
@Slf4j
public class ProxyFilter implements GlobalFilter {

    private final WebClient webClient = WebClient.create();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getPath();
        log.info("path {}", path);

        if (path.startsWith("/api/")) {
            String traceId = UUID.randomUUID().toString();
            String userId = exchange.getRequest().getHeaders().getFirst("userid");
            exchange.getRequest().mutate()
                    .header("Proxy-Id", traceId)
                    .build();

            log.info("Added Proxy-Id: {}", traceId);

            return webClient.get()
                    .uri("http://127.0.0.1:9000/api/auth")  // 替换为实际的 API 地址
                    .header("userId", userId)  // 传递 traceId
                    .accept(MediaType.APPLICATION_JSON)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                        return Mono.error(new RuntimeException("Unauthorized"));
                    })
                    .bodyToMono(String.class)
                    .flatMap(responseBody -> {
                        log.info("Authorization response: {}", responseBody);
                        return chain.filter(exchange);
                    })
                    .onErrorResume(e -> {
                        log.error("Authorization failed: {}", e.getMessage());
                        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                        return exchange.getResponse().setComplete();
                    });
        }

        return chain.filter(exchange);
    }
}
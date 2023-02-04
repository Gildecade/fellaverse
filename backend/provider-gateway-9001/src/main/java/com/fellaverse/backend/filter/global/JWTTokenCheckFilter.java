package com.fellaverse.backend.filter.global;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fellaverse.backend.config.GatewayJWTConfig;
import com.fellaverse.backend.jwt.code.JWTResponseCode;
import com.fellaverse.backend.jwt.service.JWTTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class JWTTokenCheckFilter implements GlobalFilter {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private GatewayJWTConfig gatewayJWTConfig;
    @Autowired
    private JWTTokenService jwtTokenService;
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String url = exchange.getRequest().getURI().getPath();
        // current url can be ignored
        if (this.gatewayJWTConfig.getSkipAuthUrls() != null && this.gatewayJWTConfig.getSkipAuthUrls().contains(url)) {
            return chain.filter(exchange);  // pass on
        }
        // gateway get JWT data from header; filter implemented by WebFlux
        String token = null;
        try {
            token = exchange.getRequest().getHeaders().get(this.gatewayJWTConfig.getHeaderName()).get(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("Gateway token check, Token = {}", token);
        ServerHttpResponse response = exchange.getResponse();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        response.setRawStatusCode(500);
        // if token unauthorized, gateway denied instead of microservice
        DataBuffer buffer = null;
        if (!StringUtils.hasLength(token)) {
            // create response body
            buffer = response.bufferFactory().wrap(JWTResponseCode.TOKEN_UNAUTHORIZED.getMessage().getBytes());
            log.info("No token");
            log.info(buffer.toString());
            // async return error
            return response.writeWith(Flux.just(buffer));
        } else {
            // if token verified without error, pass
            if (this.jwtTokenService.verifyToken(token)) {
                return chain.filter(exchange);
            } else {

                // if token verification fail
//                DataBuffer buffer = response.bufferFactory().wrap(JWTResponseCode.TOKEN_EXPIRED.toString().getBytes());
                buffer = response.bufferFactory().wrap(JWTResponseCode.TOKEN_EXPIRED.getMessage().getBytes());
                log.info("Wrong token");
                log.info(buffer.toString());
                // async return error
                return response.writeWith(Flux.just(buffer));
            }
        }
    }
}

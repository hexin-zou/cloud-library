package com.example.springboot.Filter;

import cn.hutool.core.util.StrUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.springboot.Feign.FeignToken;
import com.example.springboot.api.entity.Admin;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.CompletableFuture;

@Component
@Slf4j
public class JwtGlobalFilter implements GlobalFilter, Ordered {

    private static final String ERROR_CODE_401 = "401";
    private final Set<String> excludedPaths;

    @Autowired
    private FeignToken feignTokenService;

    public JwtGlobalFilter(@Value("${jwt.excluded-paths:/admin/admin/login}") String excludedPaths) {
        this.excludedPaths = new HashSet<>(Arrays.asList(excludedPaths.split(",")));
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取请求路径
        String path = exchange.getRequest().getPath().value();

        // 检查路径是否在排除列表中
        if (isExcludedPath(path)) {
            return chain.filter(exchange); // 如果是排除路径，直接放行
        }

        // 从请求头或查询参数中获取token
        String token = exchange.getRequest().getHeaders().getFirst("token");
        if (StrUtil.isBlank(token)) {
            token = exchange.getRequest().getQueryParams().getFirst("token");
        }

        // 执行认证
        if (StrUtil.isBlank(token)) {
            return handleUnauthorized(exchange, "无token，请重新登录");
        }

        // 获取 token 中的adminId
        String adminId;
        CompletableFuture<Admin> futureAdmin;
        try {
            adminId = JWT.decode(token).getAudience().get(0);
            Integer parsedAdminId = Integer.parseInt(adminId);
            futureAdmin = CompletableFuture.supplyAsync(() -> {
                try {
                    return feignTokenService.getById2(parsedAdminId);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to get admin data", e);
                }
            });
        } catch (Exception e) {
            String errMsg = "admin获取失败";
            log.error(errMsg + ", token=" + token, e);
            return handleUnauthorized(exchange, errMsg);
        }

        Admin admin = futureAdmin.join();
        if (admin == null) {
            return handleUnauthorized(exchange, "用户不存在，请重新登录2");
        }

        try {
            // 用户密码加签验证 token
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(admin.getPassword())).build();
            jwtVerifier.verify(token); // 验证token
        } catch (JWTVerificationException e) {
            return handleUnauthorized(exchange, "token验证失败，请重新登录3");
        }

        return chain.filter(exchange);
    }

    private Mono<Void> handleUnauthorized(ServerWebExchange exchange, String message) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = exchange.getResponse().bufferFactory()
                .wrap(message.getBytes(StandardCharsets.UTF_8));
        return exchange.getResponse().writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1; // 设置过滤器优先级
    }

    /**
     * 判断路径是否在排除列表中
     */
    private boolean isExcludedPath(String path) {
        for (String excludedPath : excludedPaths) {
            if (path.startsWith(excludedPath.trim())) {
                return true;
            }
        }
        return false;
    }
}
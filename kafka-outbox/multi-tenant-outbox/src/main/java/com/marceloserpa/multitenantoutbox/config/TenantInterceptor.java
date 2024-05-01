package com.marceloserpa.multitenantoutbox.config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Optional;


@Component
public class TenantInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        Long tenantId = Optional.ofNullable(request.getHeader("tenantId"))
                .map(Long::parseLong)
                .orElseThrow(() -> new RuntimeException("tenantId header is required."));

        TenantDatabase database = TenantDistribution.lookupDatabase(tenantId)
                .orElseThrow(() -> new RuntimeException("not found database for this tenantId."));

        TenantContextHolder.setDatabase(database);

        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}

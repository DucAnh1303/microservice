package com.service.elasticsearch.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.elasticsearch.common.ExceptionResponse;
import com.service.elasticsearch.config.exception.AuthenticationExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;
import java.rmi.server.ExportException;


@RequiredArgsConstructor
@Component
public class DelegatedAuthenticationEntryPoint implements AuthenticationEntryPoint {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
        String jsonResponse = objectMapper.writeValueAsString(authException.getMessage());
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.getWriter().write(jsonResponse);
    }
}

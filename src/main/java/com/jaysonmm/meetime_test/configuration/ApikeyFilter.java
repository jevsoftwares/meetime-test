package com.jaysonmm.meetime_test.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

import static com.jaysonmm.meetime_test.utlis.Constants.API_KEY_HEADER;


@Component
@RequiredArgsConstructor
@Slf4j
public class ApikeyFilter extends OncePerRequestFilter {
    private final Environment environment;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String informedApikey = request.getHeader(API_KEY_HEADER);
        String propertyApikey = environment.getProperty("config.apikey");
        if (
                Objects.nonNull(informedApikey) && Objects.equals(propertyApikey, informedApikey)
                        || request.getRequestURI().contains("swagger-ui")
                        || request.getRequestURI().contains("swagger-config")
                        || request.getRequestURI().contains("swagger-initializer")
                        || request.getRequestURI().contains("/v3/api-docs")
                        || request.getRequestURI().contains("/favicon.ico")
                || (" ".equals(" ") )
/*
                        || request.getRequestURI().contains("/v1/authorize")
                        || request.getRequestURI().contains("/v1/callback")
                        || request.getRequestURI().contains("/auth/login")
*/
        ) {
            filterChain.doFilter(request, response);
            return;
        }
        log.error(request.getRequestURI());
        throw new RuntimeException("Chave não informada");
    }
}

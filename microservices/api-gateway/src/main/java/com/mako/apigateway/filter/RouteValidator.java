package com.mako.apigateway.filter;

import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/authentication-service/register",
            "/authentication-service/test",
            "/authentication-service/token",
            "/authentication-service/request-password-reset",
            "/authentication-service/reset-password",
            "/authentication-service/eureka"
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> request.getURI().getPath().contains(uri));

}
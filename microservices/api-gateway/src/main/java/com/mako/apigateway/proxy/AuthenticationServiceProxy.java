package com.mako.apigateway.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "AUTHENTICATION-SERVICE")
public interface AuthenticationServiceProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/authentication-service/validate")
    ResponseEntity<String> validateToken(@RequestParam("token") String token);
}

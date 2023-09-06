package com.mako.apigateway.proxy;

import com.mako.apigateway.config.FeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "authentication-service", configuration = FeignConfiguration.class)
public interface AuthenticationServiceProxy {
    @RequestMapping(method = RequestMethod.GET, value = "/authentication-service/validate")
    ResponseEntity<String> validateToken(@RequestParam("token") String token);
}

package com.mako.apigateway.config;

import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.StringDecoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration {
    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new StringDecoder());
    }

    @Bean
    public Encoder feignEncoder() {
        ObjectFactory<HttpMessageConverters> messageConverters = HttpMessageConverters::new;
        return new SpringEncoder(messageConverters);
    }
}
package com.demo.config;

import feign.Logger;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig extends FeignClientsConfiguration {
    @Bean
    public Logger.Level logLevel() {
        return Logger.Level.FULL;
    }
}

package com.jjara.microservice.tag.configuration.openapi;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiGroupConfiguration {

    @Bean
    public GroupedOpenApi employeesOpenApi() {
        String[] paths = { "/tag/**" };
        return GroupedOpenApi.builder().group("tag").pathsToMatch(paths).build();
    }
}
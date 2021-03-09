/*
 * Copyright 2021, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.swagger.springfox;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableOpenApi
public class SpringFoxConfiguration {

    @Bean public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build();
    }
}
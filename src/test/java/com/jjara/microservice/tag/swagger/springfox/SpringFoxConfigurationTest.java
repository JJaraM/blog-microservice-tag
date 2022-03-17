//package com.jjara.microservice.tag.swagger.springfox;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.oas.annotations.EnableOpenApi;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import java.lang.annotation.Annotation;
//
//public class SpringFoxConfigurationTest {
//
//    @Test public void Given_nothing_When_createInstance_Then_validateAnnotations() {
//        Annotation[] annotations = SpringFoxConfiguration.class.getAnnotations();
//        Assertions.assertEquals(Configuration.class, annotations[0].annotationType());
//        Assertions.assertEquals(EnableOpenApi.class, annotations[1].annotationType());
//    }
//
//    @Test public void Given_noting_When_callApi_Then_assertOas3() {
//        SpringFoxConfiguration springFoxConfiguration = new SpringFoxConfiguration();
//        Docket docket = springFoxConfiguration.api();
//        Assertions.assertEquals(DocumentationType.OAS_30, docket.getDocumentationType());
//
//    }
//}

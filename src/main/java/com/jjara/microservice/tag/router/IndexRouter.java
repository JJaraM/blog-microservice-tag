package com.jjara.microservice.tag.router;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static com.jjara.ui.IndexPage.getIndex;
import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class IndexRouter {

    @Bean RouterFunction<ServerResponse> htmlRouter() {
        String content = getIndex(
                "Tag Web Service",
                "Tag Web Service",
                "webflux, spring-boot, java13, mongo, redis, mockito, junit",
                "https://github.com/JJaraM/blog-microservice-tag",
                "swagger-ui/");
        return route(GET("/"), request -> ok().contentType(MediaType.TEXT_HTML).bodyValue(content));
    }
}

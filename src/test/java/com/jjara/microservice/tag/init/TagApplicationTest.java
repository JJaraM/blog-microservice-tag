package com.jjara.microservice.tag.init;

import com.jjara.microservice.tag.TagApplication;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import java.lang.annotation.Annotation;

public class TagApplicationTest {

    @Test
    public void Given_nothing_When_createInstance_Then_validateAnnotations() {
        Annotation[] annotations = TagApplication.class.getAnnotations();
        Assertions.assertEquals(SpringBootApplication.class, annotations[0].annotationType());
        Assertions.assertEquals(EnableDiscoveryClient.class, annotations[1].annotationType());
    }

    @Test
    public void Given_nothing_When_getHttpTraceRepository_Then_validateInMemoryHttpTraceRepository() {
        Assertions.assertSame(new TagApplication().httpTraceRepository().getClass(), InMemoryHttpTraceRepository.class);
    }
}

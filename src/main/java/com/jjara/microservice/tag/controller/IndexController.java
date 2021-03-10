package com.jjara.microservice.tag.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static com.jjara.ui.IndexPage.getIndex;

@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${page.index.metadata.title}") private String title;
    @Value("${page.index.metadata.description}") private String description;
    @Value("${page.index.metadata.stack}") private String stack;
    @Value("${page.index.metadata.repository}") private String repository;

    @GetMapping public ResponseEntity<String> index() {
        return ResponseEntity.ok(getIndex(title, description, stack, repository, "swagger-ui/index.html"));
    }
}

package com.jjara.microservice.tag.controller;

import com.jjara.microservice.tag.domain.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import static com.jjara.ui.IndexPage.getIndex;

@RestController
@RequestMapping("/")
public class IndexController {

    @Value("${page.index.metadata.title}") private String title;
    @Value("${page.index.metadata.description}") private String description;
    @Value("${page.index.metadata.stack}") private String stack;
    @Value("${page.index.metadata.repository}") private String repository;

    @GetMapping
    public ResponseEntity<String> index() {
        var content = getIndex(title, description, stack, repository);
        return ResponseEntity.ok(content);
    }
}

/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;

import com.jjara.microservice.tag.domain.Tag;
import com.jjara.microservice.tag.service.TagService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/tag")
public class TagController {

	@Autowired private TagService service;
	
	@PostMapping
    @ResponseStatus(HttpStatus.CREATED)
	public Mono<Tag> create(@RequestBody final Tag tag) {
		return service.create(tag);
	}
	
	@GetMapping
	public Flux<Tag> findAllById(@RequestParam final List<Long> ids) {
		return service.findAllById(ids);
	}
}

/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.service;

import java.util.List;

import com.jjara.microservice.tag.domain.Tag;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TagService {
	Mono<Tag> create(Tag tag);
	Flux<Tag> findAllById(List<Long> tags);
	Mono<Tag> update(Tag tag);
}

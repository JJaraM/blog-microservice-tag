/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jjara.microservice.tag.domain.Tag;
import com.jjara.microservice.tag.repository.SequenceRepository;
import com.jjara.microservice.tag.repository.TagRepository;
import com.jjara.microservice.tag.service.TagService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TagServiceImpl implements TagService {

	@Autowired private TagRepository repository;
	@Autowired private SequenceRepository sequenceRepository;
	
	public Mono<Tag> create(Tag tag) {
		tag.setId(sequenceRepository.getNextSequenceId());
		return repository.save(tag);
	}

	@Override
	public Flux<Tag> findAllById(List<Long> tags) {
		return repository.findAllById(tags);
	}

	@Override
	public Mono<Tag> update(Tag tag) {
		return repository.save(tag);
	}

	@Override
	public Flux<Tag> findAll() {
		return repository.findAll();
	}

}

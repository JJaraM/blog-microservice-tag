/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.service.impl;

import java.util.List;
import org.springframework.stereotype.Service;
import com.jjara.microservice.tag.domain.Tag;
import com.jjara.microservice.tag.repository.SequenceRepository;
import com.jjara.microservice.tag.repository.TagRepository;
import com.jjara.microservice.tag.service.TagService;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;

@Service public class TagServiceImpl implements TagService {

	@Resource private TagRepository repository;
	@Resource private SequenceRepository sequenceRepository;

	private final String KEY = "tag";

	@Override public Mono<Tag> create(final Tag tag) {
		final var sequenceMono = sequenceRepository.getNextSequenceId(KEY);
		return sequenceMono.map(sequence -> {
			tag.setId(sequence.getSeq());
			return tag;
		}).flatMap(repository::save);
	}

	@Override public Flux<Tag> findAllById(final List<Long> tags) {
		return repository.findAllById(tags);
	}

	@Override public Mono<Tag> update(final Tag tag) {
		return repository.save(tag);
	}

	@Override public Mono<Tag> remove(final Tag tag) {
		return repository.findById(tag.getId()).flatMap(p -> repository.deleteById(p.getId()).thenReturn(p));
	}

	@Override public Flux<Tag> findAll() {
		return repository.findAll();
	}

}

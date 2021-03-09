/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.service;

import java.util.List;
import com.jjara.microservice.tag.domain.Tag;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * List of tags operations
 */
public interface TagService {

	/**
	 * Creates a tag
	 * 
	 * @param tag to be created
	 * @return an asynchronous object that contains the tag information
	 */
	Mono<Tag> create(final Tag tag);

	/**
	 * Finds a list of tags
	 * 
	 * @param tags found
	 * @return an asynchronous object that contains the list of tags
	 */
	Flux<Tag> findAllById(final List<Long> tags);

	/**
	 * Updates a tag
	 * 
	 * @param tag to be updated
	 * @return an asynchronous object with the tag updated
	 */
	Mono<Tag> update(final Tag tag);

	/**
	 * Updates a tag
	 *
	 * @param tag to be updated
	 * @return an asynchronous object with the tag updated
	 */
	Mono<Tag> remove(final Tag tag);

	/**
	 * Finds all tags
	 * 
	 * @return an asynchronous object with all tags found
	 */
	Flux<Tag> findAll();
}

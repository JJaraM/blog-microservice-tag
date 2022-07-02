/*
 *  Copyright 2022-present Jonathan Jara Morales
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
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

/**
 * Controller to access tag operations.
 */
@RestController
@RequestMapping("/tag")
public class TagController {

	/**
	 * Tag Service instance
	 */
	@Autowired private TagService service;

	/**
	 * Creates a tag
	 * 
	 * @param tag which corresponds with the post information
	 * 
	 * @return an asynchronous object which contains the tag information and returns
	 *         the create status in the header
	 */
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Mono<Tag> create(@RequestBody final Tag tag) {
		return service.create(tag);
	}

	/**
	 * Finds a list of tags according with the list of ids, this operations
	 * retrieves the list of post that are related with the tags id.
	 * 
	 * @param ids list ids to search
	 * @return an asynchronous object that contains the list of tags with its ids.
	 */
	@GetMapping("/byIds")
	public Flux<Tag> findAllById(@RequestParam final List<Long> ids) {
		return service.findAllById(ids);
	}

	/**
	 * Finds all tags
	 * 
	 * @return an asynchronous object with the information of all tags
	 */
	@GetMapping("/all")
	public Flux<Tag> findAll() {
		return service.findAll();
	}
}

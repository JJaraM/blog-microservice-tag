/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.jjara.microservice.tag.domain.Tag;

@Repository
public interface TagRepository extends ReactiveMongoRepository<Tag, Long> {

}
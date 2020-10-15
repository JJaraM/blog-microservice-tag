/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.jjara.microservice.tag.domain.Sequence;
import reactor.core.publisher.Mono;

/**
 * Mongo Repository used to create the tag's id
 */
@Repository
public class SequenceRepository {

	@Autowired private ReactiveMongoTemplate mongoTemplate;

	public Mono<Sequence> getNextSequenceId(final String key) {

		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		Mono<Sequence> seqId = mongoTemplate.findAndModify(query, update, options, Sequence.class);

		return seqId;

	}

}

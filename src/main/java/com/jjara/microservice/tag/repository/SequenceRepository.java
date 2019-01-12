/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.jjara.microservice.tag.domain.Sequence;

/**
 * Mongo Repository used to create the tag's id
 */
@Repository
public class SequenceRepository {

	/**
	 * Mongo operations instance
	 */
	@Autowired
	private MongoOperations mongoOperation;

	/**
	 * Name of the sequence
	 */
	private final String KEY = "tag";

	/**
	 * Gets the next sequence id
	 * 
	 * @return sequence's id
	 */
	public long getNextSequenceId() {
		// get sequence id
		final Query query = new Query(Criteria.where("_id").is(KEY));

		// increase sequence id by 1
		final Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		final FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		final Sequence seqId = mongoOperation.findAndModify(query, update, options, Sequence.class);

		return seqId.getSeq();
	}

}

/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.domain;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of the primary key for a mongo document
 */
@Document(collection = "tag_sequence")
public class Sequence {

	/**
	 * Primary key of this document
	 */
	private String id;

	/**
	 * Sequence of the document, this will be used as a primary key for the tags
	 */
	private long seq;

	/**
	 * Gets the id
	 * 
	 * @return an id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the sequence
	 * 
	 * @return
	 */
	public long getSeq() {
		return seq;
	}

	/**
	 * Sets the sequence
	 * 
	 * @param seq
	 */
	public void setSeq(long seq) {
		this.seq = seq;
	}

}

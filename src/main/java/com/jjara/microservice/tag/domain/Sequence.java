/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of the primary key for a mongo document
 */
@Getter @Setter
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

}

/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.domain;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of the tag document
 */
@Document
@Getter @Setter
public class Tag {

	/**
	 * Tag's id
	 */
	@Id private long id;
	
	/**
	 * Tag's name
	 */
	private String name;
	
	/**
	 * List of posts
	 */
	private List<Long> posts = new ArrayList<>();
	
	/**
	 * Adds a post id into the list of tags
	 * @param post which represent a post id that already exists
	 * @return the id of the post that was inserted into the list
	 */
	public Long addPost(Long post) {
		if (getPosts() == null) {
			setPosts(new ArrayList<>());
		}
		getPosts().add(post);
		return post;
	}

}

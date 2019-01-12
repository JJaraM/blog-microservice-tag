/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Representation of the tag document
 */
@Document
public class Tag {

	/**
	 * Tag's id
	 */
	@Id
	private long id;
	
	/**
	 * Tag's name
	 */
	private String name;
	
	/**
	 * List of posts
	 */
	private List<Long> posts;

	/**
	 * Gets the id
	 * @return an id
	 */
	public long getId() {
		return id;
	}

	/**
	 * Sets the id
	 * @param id
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * Gets the name
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the posts
	 * @return a list of post ids
	 */
	public List<Long> getPosts() {
		return posts;
	}

	/**
	 * Sets the post
	 * @param posts
	 */
	public void setPosts(List<Long> posts) {
		this.posts = posts;
	}
	
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

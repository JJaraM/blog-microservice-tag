/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjara.microservice.tag.service.TagService;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;

/**
 * Handles post subscriptions
 */
@Configuration
public class TagSubscription {

	/**
	 * Tag Service Layer
	 */
	@Autowired
	private TagService tagService;

	/**
	 * Class that provides the functionality to read JSON
	 */
	@Autowired
	private ObjectMapper mapper;

	/**
	 * URL used to connect to redis
	 */
	@Value("${spring.data.redis.url}")
	private String url;

	/**
	 * Name of the channel
	 */
	@Value("${spring.data.redis.channel-tag}")
	private String channelTag;

	/**
	 * Creates the subscription for the portfolio tag and makes an update which
	 * corresponds in associate the tag when there is a new post
	 * 
	 * @return the subscription instance
	 */
	@Bean
	public RedisPubSubCommands<String, String> subscribe() {
		final var client = RedisClient.create(url);
		final var connection = client.connectPubSub();

		// Listener that we catch the result and will execute the task
		var listener = new RedisPubSubAdapter<String, String>() {
			public void message(String channel, String messageBody) {
				try {
					var message = mapper.readValue(messageBody, SubscriberMessage.class);
					tagService.findAllById(message.getTags()).map(tag -> {
						if (!tag.getPosts().contains(message.getPostId())) {
							tag.addPost(message.getPostId());
						}
						return tag;
					}).flatMap(tagService::update).subscribe();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		};
		connection.addListener(listener);
		var sync = connection.sync();
		sync.subscribe(channelTag);
		return sync;
	}

	/**
	 * Represents the information of the message that we received from the channel
	 */
	public static final class SubscriberMessage {

		protected long postId;
		protected List<Long> tags;

		public long getPostId() {
			return postId;
		}

		public void setPostId(long postId) {
			this.postId = postId;
		}

		public List<Long> getTags() {
			return tags;
		}

		public void setTags(List<Long> tags) {
			this.tags = tags;
		}

	}
}

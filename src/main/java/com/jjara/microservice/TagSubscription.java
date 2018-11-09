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
 * Handle all subscriptions
 * @author jonathan
 */
@Configuration
public class TagSubscription {
	
	@Autowired private TagService tagService;
	@Autowired private ObjectMapper mapper;
	
	@Value("${spring.data.redis.url}") private String url;
	@Value("${spring.data.redis.channel-tag}") private String channelTag;
	
	@Bean
	public RedisPubSubCommands<String, String> subscribe() {
		var client = RedisClient.create(url);
		var con = client.connectPubSub();
		
		var listener = new RedisPubSubAdapter<String, String>() {
		    public void message(String channel, String messageBody) {
				try {
					var message = mapper.readValue(messageBody, SubscriberMessage.class);
			    	tagService.findAllById(message.getTags()).map(tag -> {
			    		tag.addPost(message.getPostId());
			    		return tag;
			    	}).flatMap(tagService::update).subscribe();
				} catch (IOException e) {
					e.printStackTrace();
				}				
		    }
		};
		con.addListener(listener);
		var sync = con.sync();
		sync.subscribe(channelTag);
		return sync;
	}
	
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

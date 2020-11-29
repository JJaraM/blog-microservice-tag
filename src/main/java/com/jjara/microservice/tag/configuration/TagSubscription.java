/*
 * Copyright 2018, Jonathan Jara Morales, All rights reserved.
 */
package com.jjara.microservice.tag.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import com.jjara.microservice.tag.domain.Tag;
import io.lettuce.core.pubsub.StatefulRedisPubSubConnection;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjara.microservice.tag.service.TagService;

import io.lettuce.core.RedisClient;
import io.lettuce.core.pubsub.RedisPubSubAdapter;
import io.lettuce.core.pubsub.api.sync.RedisPubSubCommands;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Handles post subscriptions
 */
@Configuration
public class TagSubscription {

	@Resource private TagService tagService;
	@Resource private ObjectMapper mapper;
	@Value("${spring.redis.channels.channel-tag-add}") private String channelTagAdd;
	@Value("${spring.redis.channels.channel-tag-remove}") private String channelTagRemove;
	@Resource private RedisClient client;
	private Map<String, BiFunction<Tag, Long, Mono<Tag>>> channelFunctions = new HashMap();
	private StatefulRedisPubSubConnection<String, String> connection;

	@PostConstruct
	void init() {
		connection = client.connectPubSub();
	}

	/**
	 * Creates the subscription for the portfolio tag and makes an update which
	 * corresponds in associate the tag when there is a new post
	 * 
	 * @return the subscription instance
	 */
	@Bean
	public RedisPubSubCommands<String, String> addTag() {
		final BiFunction<Tag, Long, Mono<Tag>> add = (tag, postId) -> tagService.update(tag);
		var listener = new Event(channelTagAdd, add);
		connection.addListener(listener);
		var sync = connection.sync();
		sync.subscribe(channelTagAdd);
		return sync;
	}

	@Bean
	public RedisPubSubCommands<String, String> removeTag() {
		final BiFunction<Tag, Long, Mono<Tag>> remove = (tag, postId) -> {
			Mono<Tag> mono = Mono.just(tag);
			if (tag.getPosts().contains(postId)) {
				tag.getPosts().remove(postId);
				if (tag.getPosts().size() == 0) {
					mono = tagService.remove(tag);
				} else {
					mono = tagService.update(tag);
				}
			}
			return mono;
		};

		var listener = new Event(channelTagRemove, remove);
		connection.addListener(listener);

		var sync = connection.sync();
		sync.subscribe(channelTagRemove);
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

	public final class Event extends RedisPubSubAdapter<String, String> {

		private Event(final String channel, final BiFunction<Tag, Long, Mono<Tag>> function) {
			channelFunctions.put(channel, function);
		}

		@Override
		public void message(final String channel, final String messageBody) {
			try {
				var message = mapper.readValue(messageBody, SubscriberMessage.class);
				tagService.findAllById(message.getTags()).map(tag -> {
					if (!tag.getPosts().contains(message.getPostId())) {
						tag.addPost(message.getPostId());
					}
					return tag;
				}).flatMap(tag -> {
					BiFunction<Tag, Long, Mono<Tag>> mono = channelFunctions.get(channel);
					return mono.apply(tag, message.getPostId());
				}).subscribe();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}

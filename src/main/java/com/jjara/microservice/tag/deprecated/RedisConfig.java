/*package com.jjara.microservice.tag.deprecated;

import java.util.concurrent.Executors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import com.jjara.microservice.tag.service.TagService;

@Configuration
public class RedisConfig {
	
	@Autowired private TagService tagService;
	
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		return new JedisConnectionFactory();
	}
	
	@Bean
	public RedisMessageListenerContainer container() {
		final var container = new RedisMessageListenerContainer();
		container.setConnectionFactory(jedisConnectionFactory());
		container.addMessageListener(messageListener(), topic());
		container.setTaskExecutor(Executors.newFixedThreadPool(4));
		return container;
	}
	
	@Bean
	public MessageListenerAdapter messageListener() {
		return new MessageListenerAdapter(new InfoSubscriber(tagService));
	}
	
	@Bean
	public ChannelTopic topic() {
		return new ChannelTopic("jjara:tag-channel");
	}

}*/
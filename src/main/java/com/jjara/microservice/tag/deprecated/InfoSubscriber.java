package com.jjara.microservice.tag.deprecated;

import java.io.IOException;
import java.util.List;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jjara.microservice.tag.service.TagService;

public class InfoSubscriber implements MessageListener {

	private TagService tagService;
	private ObjectMapper mapper;
	
	public InfoSubscriber(TagService tagService) {
		this.tagService = tagService;
		this.mapper = new ObjectMapper();
	}

	@Override
	public void onMessage(Message message, byte[] pattern) {
		try {
			var subscriberMessage = mapper.readValue(message.getBody(), SubscriberMessage.class);
			var tags = tagService.findAllById(subscriberMessage.getTags());
		} catch (IOException e) {
			e.printStackTrace();
		}
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

package com.jjara.microservice.tag.configuration;

import com.mongodb.reactivestreams.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;

/**
 * Mongo configuration class that is needed to start the mongo operations
 */
@Configuration
public class MongoConfig {

    @Autowired
    private MongoClient mongoClient;
    @Value("${spring.data.mongodb.databaseName}") private String databaseName;

    /**
     * Gets the template that is used by spring to execute the distinct operations.
     * @return a mongo template instance
     */
    @Bean
    public ReactiveMongoTemplate reactiveMongoTemplate() {
        return new ReactiveMongoTemplate(mongoClient, databaseName);
    }
}

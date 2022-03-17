//package com.jjara.microservice.tag.configuration.mongo;
//
//import com.mongodb.reactivestreams.client.MongoClient;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
//
///**
// * Mongo configuration class that is needed to start the mongo operations
// */
//public class MongoConfig {
//
//    /**
//     * Gets the template that is used by spring to execute the distinct operations.
//     * @return a mongo template instance
//     */
//    @Bean public ReactiveMongoTemplate reactiveMongoTemplate(MongoClient mongoClient, @Value("${spring.data.mongodb.databaseName}") String databaseName) {
//        return new ReactiveMongoTemplate(mongoClient, databaseName);
//    }
//}
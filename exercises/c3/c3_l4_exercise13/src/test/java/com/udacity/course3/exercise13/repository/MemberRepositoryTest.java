package com.udacity.course3.exercise13.repository;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;

@DataMongoTest
@EnableAutoConfiguration
public class MemberRepositoryTest {

    @Autowired MongoTemplate mongoTemplate;

    @Test
    public void contextLoads() {
    }


}
package com.udacity.course3.exercise10.repository;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;



@RunWith(SpringRunner.class)
@DataMongoTest
public class MemberRepositoryTest {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        DBObject member = BasicDBObjectBuilder.start()
                .add("key", "value")
                .get();

        mongoTemplate.save(member, "members");

        assertThat(mongoTemplate.findAll(DBObject.class,"members").size()).isEqualTo(1);
    }
}

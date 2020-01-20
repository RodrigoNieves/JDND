package com.udacity.course3.lesson6.exercise4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Arrays;

public class Application {

    public static void main(String[] args) {
        // STEP 1: Craft the URI to connect to your local MongoDB server
        // Host: localhost
        // Port: 27017 (default)
        // Username: course3
        // Password: course3
        // DB: jdnd-c3
        String uri = "mongodb://course3:course3@localhost:27017/jdnd-c3";

        // STEP 2: Create a MongoClient
        MongoClient mongoClient = MongoClients.create(uri);


        // STEP 3: Select the jdnd-c3 database to work with
        MongoDatabase database = mongoClient.getDatabase("jdnd-c3");

        // Perform all the steps listed in the exercise
        MongoCollection<Document> members = database.getCollection("members");

        members.find(new Document("last_name","Khan"))
                .iterator()
                .forEachRemaining(doc -> System.out.println("Kahn: " + doc));


        members.find(new Document("$and",
                Arrays.asList(
                        new Document("last_name", "Doe"),
                        new Document("gender", "female"))))
                .iterator()
                .forEachRemaining(doc -> System.out.println("Doe female: " +doc));


        members.find(new Document("interests", "golf"))
                .iterator()
                .forEachRemaining(doc -> System.out.println("Likes golf: " + doc));

        members.find(new Document("address.state","MN"))
                .iterator()
                .forEachRemaining(doc -> System.out.println("Live in MN: " + doc));
        // IMPORTANT: Make sure to close the MongoClient at the end so your program exits.
        mongoClient.close();
    }

}
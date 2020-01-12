package com.udacity.course3.lesson6.exercise4;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class Application {

    public static void main(String[] args) {
        // STEP 1: Craft the URI to connect to your local MongoDB server
        // Host: localhost
        // Port: 27017 (default)
        // Username: course3
        // Password: course3
        // DB: jdnd-c3
        String uri = "mongodb://course3:course3@localhost:27017//jdnd-c3";

        // STEP 2: Create a MongoClient
        MongoClient mongoClient = (MongoClient) MongoClients.create(uri);


        // STEP 3: Select the jdnd-c3 database to work with
        MongoDatabase database = mongoClient.getDatabase("jdnd-c3");

        // Perform all the steps listed in the exercise


        // IMPORTANT: Make sure to close the MongoClient at the end so your program exits.
        mongoClient.close();
    }

}
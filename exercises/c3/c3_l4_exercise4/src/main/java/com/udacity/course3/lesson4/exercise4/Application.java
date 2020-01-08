package com.udacity.course3.lesson4.exercise4;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        database.createCollection("members");

        MongoCollection<Document> members = database.getCollection("members");

        Document member = new Document()
                .append("firts_name","Carl")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        members.insertOne(member);
        System.err.println(member.toString());

        Document member2 = new Document()
                .append("_id", "MyId")
                .append("firts_name","Rodrigo")
                .append("last_name", "Santiago")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        members.insertOne(member2);
        System.err.println(member2.toString());

        List<Document> documents = new ArrayList<Document>();
        Document member3 = new Document()
                .append("firts_name","member3")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        documents.add(member3);
        Document member4 = new Document()
                .append("firts_name","member4")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        documents.add(member4);
        Document member5 = new Document()
                .append("firts_name","member5")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        documents.add(member5);
        Document member6 = new Document()
                .append("firts_name","member6")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        documents.add(member6);
        Document member7 = new Document()
                .append("firts_name","member7")
                .append("last_name", "Jenkins")
                .append("gender", "male")
                .append("age", 23)
                .append("address", new Document()
                        .append("street", "123 Main Street")
                        .append("city", "Oakland")
                        .append("state", "CA"))
                .append("interest", Arrays.asList("pilates", "swim", "crossfit"))
                .append("balance", 125.20);
        documents.add(member7);
        members.insertMany(documents);


        members.updateMany(new Document(), new Document()
                .append("$rename",new Document().append("gender","sex")));

        members.deleteOne(new Document().append("_id","MyId"));

        members.deleteOne(new Document().append("sex","male"));
        // IMPORTANT: Make sure to close the MongoClient at the end so your program exits.
    }

}
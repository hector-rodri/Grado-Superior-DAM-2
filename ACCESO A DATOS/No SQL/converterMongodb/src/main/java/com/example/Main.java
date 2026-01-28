package com.example;

import com.mongodb.client.*;//Import all libraries
import org.bson.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017/practica_java";//MongoDB connection URI
        FormatConverter converter = new FormatConverter();//Instance of FormatConverter

        try (MongoClient mongoClient = MongoClients.create(uri)) {//Create MongoClient
            MongoDatabase database = mongoClient.getDatabase("practica_java");//Get database
            MongoCollection<Document> collection = database.getCollection("elements");//Get collection

            try (MongoCursor<Document> cursor = collection.find().cursor()) {//Create cursor to iterate over documents
                while (cursor.hasNext()) {//Iterate through documents
                    Document document = cursor.next();//Get next document
                    String jsonString = document.toJson();//Get JSON string from document

                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);//Get JSONObject from string
                        String xml = converter.convertirJsonAXml(jsonObject);//Convert JSON to XML
                        System.out.println(xml);//Print XML
                    } catch (JSONException e) {//Handle JSON parsing exceptions
                        System.err.println("Error parsing JSON: " + e.getMessage());
                    }
                }
            }

        } catch (Exception e) {//Handle MongoDB connection exceptions
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }
}



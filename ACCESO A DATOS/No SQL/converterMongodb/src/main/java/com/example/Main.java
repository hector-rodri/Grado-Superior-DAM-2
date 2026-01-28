package com.example;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.JSONObject;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        String uri = "mongodb://localhost:27017/practica_java";
        FormatConverter converter = new FormatConverter();

        try (MongoClient mongoClient = MongoClients.create(uri)) {
            MongoDatabase database = mongoClient.getDatabase("practica_java");
            MongoCollection<Document> collection = database.getCollection("elements");

            try (MongoCursor<Document> cursor = collection.find().cursor()) {
                while (cursor.hasNext()) {
                    Document document = cursor.next();
                    String jsonString = document.toJson();

                    try {
                        JSONObject jsonObject = new JSONObject(jsonString);
                        String xml = converter.convertirJsonAXml(jsonObject);
                        System.out.println(xml); 
                    } catch (JSONException e) {
                        System.err.println("Error en parsear JSON: " + e.getMessage());
                    }
                }
            }

        } catch (Exception e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }
}



package com.example;

import org.json.JSONObject;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        FormatConverter converter = new FormatConverter();

        File fileXml = new File("src/main/resources/messi.xml");
        File fileJson = new File("src/main/resources/messi.json");
        File fileBson = new File("src/main/resources/messi.bson");
        File fileJsonFromBson = new File("src/main/resources/messi_from_bson.json");

        try {
            // XML -> JSON
            FileInputStream fis = new FileInputStream(fileXml);
            String xmlContent = new String(fis.readAllBytes());
            fis.close();

            JSONObject json = converter.convertirXmlAJson(xmlContent);

            FileWriter fw = new FileWriter(fileJson);
            fw.write(json.toString(4));
            fw.close();

            // JSON -> BSON
            byte[] bsonData = converter.jsonToBson(json);
            FileOutputStream fos = new FileOutputStream(fileBson);
            fos.write(bsonData);
            fos.close();

            // BSON -> JSON
            fis = new FileInputStream(fileBson);
            byte[] bsonRead = fis.readAllBytes();
            fis.close();

            JSONObject jsonRecovered = converter.bsonToJson(bsonRead);

            fw = new FileWriter(fileJsonFromBson);
            fw.write(jsonRecovered.toString(4));
            fw.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

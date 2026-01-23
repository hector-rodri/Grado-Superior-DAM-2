package com.example;

import java.io.*;//Import all libraries
import org.json.*;

public class Main {
    public static void main(String[] args) {
        File fileXml = new File("src/main/resources/messi.xml");//Input XML file
        File fileNewJson = new File("src/main/resources/messi.json");//Output JSON file
        File fileJson = new File("src/main/resources/neymar.json");//Input JSON file
        File fileNewXml = new File("src/main/resources/neymar.xml");//Output XML file
        File fileBson = new File("src/main/resources/messi.bson");//Input BSON file
        File fileNewBson = new File("src/main/resources/neymar.bson");//Output BSON file

        FormatConverter converter = new FormatConverter();//Create FormatConverter object

        try {
            //XML to JSON
            FileReader fileReader = new FileReader(fileXml);//Read XML file
            int c;
            String xmlContent = "";
            while ((c = fileReader.read()) != -1) {//Read each character of the file
                xmlContent += (char) c;//Append character to string
            }
            fileReader.close();

            JSONObject json = converter.convertirXmlAJson(xmlContent);//Convert XML to JSON
            FileWriter fileWriter = new FileWriter(fileNewJson);//Write JSON to file
            fileWriter.write(json.toString(4));//Write JSON content
            fileWriter.close();

            //JSON to XML
            fileReader = new FileReader(fileJson);//Read JSON file (neymar.json)
            String jsonContent = "";
            while ((c = fileReader.read()) != -1) {//Read each character of the file
                jsonContent += (char) c;//Append character to string
            }
            fileReader.close();

            JSONObject jsonObject = new JSONObject(jsonContent);//Convert string to JSON object
            String xml = converter.convertirJsonAXml(jsonObject);//Convert JSON to XML
            fileWriter = new FileWriter(fileNewXml);//Write XML to file
            fileWriter.write(xml);//Write XML content
            fileWriter.close();

            //JSON to BSON (using neymar.json as input and neymar.bson as output)
            fileReader = new FileReader(fileJson);//Read JSON file (neymar.json)
            String jsonContentForBson = "";
            while ((c = fileReader.read()) != -1) {
                jsonContentForBson += (char) c;
            }
            fileReader.close();

            JSONObject jsonForBson = new JSONObject(jsonContentForBson);
            byte[] bsonData = converter.jsonToBson(jsonForBson);//Convert JSON to BSON
            FileOutputStream fos = new FileOutputStream(fileNewBson);//Write BSON file (neymar.bson)
            fos.write(bsonData);
            fos.close();

            //BSON to JSON (using messi.bson as input and messi.json as output)
            FileInputStream fis = new FileInputStream(fileBson);//Read BSON file (messi.bson)
            byte[] bsonRead = fis.readAllBytes();
            fis.close();

            JSONObject jsonFromBson = converter.bsonToJson(bsonRead);//Convert BSON to JSON

            // Write recovered JSON to messi.json (overwriting or new)
            fileWriter = new FileWriter(fileNewJson);//Write JSON to file (messi.json)
            fileWriter.write(jsonFromBson.toString(4));//Write JSON content
            fileWriter.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

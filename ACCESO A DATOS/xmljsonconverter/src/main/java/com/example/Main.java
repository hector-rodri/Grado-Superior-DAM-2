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
            //XML to JSON (using messi.xml as input and messi.json as output)
            FileReader fileReader = new FileReader(fileXml);//Read XML file
            int c;
            String xmlContent = "";
            while ((c = fileReader.read()) != -1) {//Read each character of the file
                xmlContent += (char) c;//Append character to string
            }
            System.out.println("XML content:");
            System.out.println(xmlContent);
            JSONObject json = converter.convertirXmlAJson(xmlContent);//Convert XML to JSON
            System.out.println("Converted JSON:");
            System.out.println(json.toString(4));
            FileWriter fileWriter = new FileWriter(fileNewJson);//Write JSON to file
            fileWriter.write(json.toString(4));//Write JSON content
            fileWriter.close();
            fileReader.close();
            System.out.println("XML to JSON conversion completed");

            //JSON to XML (using neymar.json as input and neymar.xml as output)
            fileReader = new FileReader(fileJson);//Read JSON file 
            String jsonContent = "";
            while ((c = fileReader.read()) != -1) {//Read each character of the file
                jsonContent += (char) c;//Append character to string
            }
            System.out.println("JSON content:");
            System.out.println(jsonContent);
            JSONObject jsonObject = new JSONObject(jsonContent);//Convert string to JSON object
            String xml = converter.convertirJsonAXml(jsonObject);//Convert JSON to XML
            System.out.println("Converted XML:");
            System.out.println(xml);
            fileWriter = new FileWriter(fileNewXml);//Write XML to file
            fileWriter.write(xml);//Write XML content
            fileWriter.close();
            fileReader.close();
            System.out.println("JSON to XML conversion completed");

            //JSON to BSON (using neymar.json as input and neymar.bson as output)
            fileReader = new FileReader(fileJson);//Read JSON file
            String jsonContentForBson = "";
            while ((c = fileReader.read()) != -1) {//Read each character of the file
                jsonContentForBson += (char) c;//Append character to string
            }
            System.out.println("JSON content for BSON:");
            System.out.println(jsonContentForBson);
            JSONObject jsonForBson = new JSONObject(jsonContentForBson);//Convert string to JSON object
            byte[] bsonData = converter.jsonToBson(jsonForBson);//Convert JSON to BSON
            FileOutputStream fileOutputStream = new FileOutputStream(fileNewBson);//Write BSON file 
            fileOutputStream.write(bsonData);//Write BSON content
            fileOutputStream.close();
            fileReader.close();
            System.out.println("JSON to BSON conversion completed");

            //BSON to JSON (using messi.bson as input and messi.json as output)
            FileInputStream fileInputStream = new FileInputStream(fileBson);//Read BSON file 
            byte[] bsonRead = fileInputStream.readAllBytes();//Read all bytes
            JSONObject jsonFromBson = converter.bsonToJson(bsonRead);//Convert BSON to JSON
            System.out.println("JSON content from BSON:");
            System.out.println(jsonFromBson.toString(4));
            fileWriter = new FileWriter(fileNewJson);//Write JSON to file 
            fileWriter.write(jsonFromBson.toString(4));//Write JSON content
            fileWriter.close();
            fileInputStream.close();
            System.out.println("BSON to JSON conversion completed");
    
        } catch (Exception e) {//Handle exceptions
            System.err.println("Error: " + e.getMessage());
        }
    }
}

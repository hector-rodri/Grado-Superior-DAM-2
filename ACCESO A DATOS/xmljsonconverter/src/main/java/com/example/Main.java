package com.example;

import java.io.*;
import org.json.*;

public class Main {
    public static void main(String[] args) {
        Xml2Json xml2Json = new Xml2Json();
        Json2Xml json2Xml = new Json2Xml();

        File fileXml = new File("src/main/resources/messi.xml");
        File fileNewJson = new File("src/main/resources/messi.json");
        File fileJson = new File("src/main/resources/neymar.json");
        File fileNewXml = new File("src/main/resources/neymar.xml");

        try {
            //XML to JSON
            FileReader fileReader = new FileReader(fileXml);
            int c;
            String xmlContent = "";
            while ((c = fileReader.read()) != -1) {
                xmlContent += (char) c;
            }
            fileReader.close();

            JSONObject json = xml2Json.convertirXmlAJson(xmlContent);
            FileWriter fileWriter = new FileWriter(fileNewJson);
            fileWriter.write(json.toString(4));
            fileWriter.close();

            //JSON to XML
            fileReader = new FileReader(fileJson);
            String jsonContent = "";
            while ((c = fileReader.read()) != -1) {
                jsonContent += (char) c;
            }
            fileReader.close();

            JSONObject jsonObject = new JSONObject(jsonContent);
            String xml = json2Xml.convertirJsonAXml(jsonObject);
            fileWriter = new FileWriter(fileNewXml);
            fileWriter.write(xml);
            fileWriter.close();

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}

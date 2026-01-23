package com.example;

import org.json.*;//Import all libararies
import org.bson.*;

public class FormatConverter {//Class FormatConverter

    public JSONObject convertirXmlAJson(String xml) throws Exception {//XML to JSON
        try {
            return XML.toJSONObject(xml);//Convert XML to JSON
        } catch (Exception ex) {
            throw new Exception("Invalid XML  " + ex.getMessage());//Throw exception if XML is invalid
        }
    }

    public String convertirJsonAXml(JSONObject json) throws Exception {//JSON to XML
        try {
            return XML.toString(json);//Convert JSON to XML
        } catch (Exception ex) {
            throw new Exception("Invalid JSON" + ex.getMessage());//Throw exception if JSON is invalid
        }
    }

    public byte[] jsonToBson(JSONObject json) throws Exception {//JSON to BSON
        try {
            Document doc = Document.parse(json.toString());//Parse JSON to Document
            return doc.toJson().getBytes();//Convert Document to byte array
        } catch (Exception e) {
            throw new Exception("Invalid JSON for BSON: " + e.getMessage());//Throw exception if JSON is invalid for BSON
        }
    }

    public JSONObject bsonToJson(byte[] bson) throws Exception {//BSON to JSON
        try {
            String jsonString = new String(bson);//Convert byte array to String
            Document doc = Document.parse(jsonString);//Parse String to Document
            return new JSONObject(doc.toJson());//Convert Document to JSON
        } catch (Exception e) {
            throw new Exception("Invalid BSON: " + e.getMessage());//Throw exception if BSON is invalid
        }
    }
}

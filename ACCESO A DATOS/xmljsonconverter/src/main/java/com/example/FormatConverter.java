package com.example;

import org.json.*;
import org.bson.*;

public class FormatConverter {

    public JSONObject convertirXmlAJson(String xml) throws Exception {// XML to JSON
        try {
            return XML.toJSONObject(xml);
        } catch (Exception ex) {
            throw new Exception("Invalid XML  " + ex.getMessage());
        }
    }

    public String convertirJsonAXml(JSONObject json) throws Exception {// JSON to XML
        try {
            return XML.toString(json);
        } catch (Exception ex) {
            throw new Exception("Invalid JSON" + ex.getMessage());
        }
    }

    public byte[] jsonToBson(JSONObject json) throws Exception {
        try {
            Document doc = Document.parse(json.toString());
            return doc.toJson().getBytes();
        } catch (Exception e) {
            throw new Exception("Invalid JSON for BSON: " + e.getMessage());
        }
    }

    public JSONObject bsonToJson(byte[] bson) throws Exception {
        try {
            String jsonString = new String(bson);
            Document doc = Document.parse(jsonString);
            return new JSONObject(doc.toJson());
        } catch (Exception e) {
            throw new Exception("Invalid BSON: " + e.getMessage());
        }
    }
}

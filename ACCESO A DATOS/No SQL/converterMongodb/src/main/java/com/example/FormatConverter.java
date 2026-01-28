package com.example;

import org.json.*;//Import all libraries

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
}

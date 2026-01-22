package com.example;

import org.json.*;

public class Xml2Json {

    public JSONObject convertirXmlAJson(String xml) throws Exception {
        try {
            return XML.toJSONObject(xml);
        } catch (Exception ex) {
            throw new Exception("Invalid XML  " + ex.getMessage());
        }
    }
}

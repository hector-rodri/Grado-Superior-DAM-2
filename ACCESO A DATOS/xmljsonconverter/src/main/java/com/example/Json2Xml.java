package com.example;

import org.json.*;

public class Json2Xml {
    public String convertirJsonAXml(JSONObject json) throws Exception {
        try {
            return XML.toString(json);
        } catch (Exception ex) {
            throw new Exception("Invalid JSON" + ex.getMessage());
        }
    }
}

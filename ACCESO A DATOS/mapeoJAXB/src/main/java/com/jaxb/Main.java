package com.jaxb;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("recursos/players.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(Catalog.class);

            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Catalog catalog = (Catalog) jaxbUnmarshaller.unmarshal(file);

            for (Player player : catalog.getPlayer()) {
                System.out.println(player);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
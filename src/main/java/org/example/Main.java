package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import xml.xmlParser;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        xmlParser xmlParser = new xmlParser();
        xmlParser.saveContratosToDB();
        SpringApplication.run(Main.class, args);
    }
}
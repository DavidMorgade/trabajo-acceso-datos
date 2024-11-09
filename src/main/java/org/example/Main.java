package org.example;


import org.example.db.db;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        db db = new db();
        SpringApplication.run(Main.class, args);
    }
}
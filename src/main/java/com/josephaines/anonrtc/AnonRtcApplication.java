package com.josephaines.anonrtc;

import org.json.simple.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileWriter;
import java.io.IOException;

@SpringBootApplication
public class AnonRtcApplication {

    public static void main(String[] args) throws IOException {
        System.out.println("Starting\nCreating JSON File");
        JSONObject data = new JSONObject();
        data.put("sessionAmount", 0L);
        data.put("newId", false);
        FileWriter file = new FileWriter("data.json");
        file.write(data.toJSONString());
        file.close();
        System.out.println("JSON File created");
        SpringApplication.run(AnonRtcApplication.class, args);
    }

}

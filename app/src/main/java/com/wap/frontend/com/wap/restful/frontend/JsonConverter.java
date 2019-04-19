package com.wap.frontend.com.wap.restful.frontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.util.Optional;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;

public class JsonConverter {

    public static JsonObject parse(InputStream input) {
        StringBuilder sb = new StringBuilder();
        JsonObject ob;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = in.readLine()) != null) {
                sb.append(line);
            }
            JsonReader reader = Json.createReader(new StringReader(sb.toString()));
            ob = reader.readObject();
            reader.close();
            return ob;
        } catch (IOException e) {
            // TODO add logging
            e.printStackTrace();
            System.out.println("Error while parsing information");
            return null;
        }

    }

}
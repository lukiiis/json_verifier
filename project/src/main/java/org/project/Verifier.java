package org.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Verifier {
    public boolean verifyJson(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode rootNode = objectMapper.readTree(new File(path));
            JsonNode statements = rootNode.get("PolicyDocument").get("Statement");
            for (JsonNode statement:statements) {
                return !statement.get("Resource").asText().equals("*");
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return false;
    }
}
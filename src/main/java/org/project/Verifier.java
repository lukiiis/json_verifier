package org.project;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Verifier {
//    public boolean verifyJson(String path){
//        ObjectMapper objectMapper = new ObjectMapper();
//        try{
//            JsonNode rootNode = objectMapper.readTree(new File(path));
//            JsonNode statements = rootNode.get("PolicyDocument").get("Statement");
//            for (JsonNode statement:statements) {
//                //return !statement.get("Resource").asText().equals("*");
//                if(statement.get("Resource").asText().equals("*"))
//                    return false;
//            }
//            return true;
//        }
//        catch (IOException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

    public void verifyJson(String path){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            JsonNode rootNode = objectMapper.readTree(new File(path));

            if(!verifyFile(rootNode)){
                System.out.println("Provided file is not a proper IAM Role Policy.");
                return;
            }

            JsonNode statements = rootNode.get("PolicyDocument").get("Statement");
            for (JsonNode statement:statements) {
                System.out.println(verifyResource(statement));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public boolean verifyResource(JsonNode statement){
        if(statement.get("Resource") == null)
            return false;

        return !statement.get("Resource").asText().equals("*");
    }

    public boolean verifyFile(JsonNode rootNode){
        return rootNode.get("PolicyDocument") != null && rootNode.get("PolicyDocument").get("Statement") != null;
    }


    public static void main(String[] args) {
        String filePath = "src/main/resources/aws_iam_role.json";
        Verifier verifier = new Verifier();
        verifier.verifyJson(filePath);
    }
}
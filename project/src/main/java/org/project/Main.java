package org.project;

public class Main {
    public static void main(String[] args) {
        String filePath = "project/src/main/resources/aws_iam_role.json";
        Verifier verifier = new Verifier();
        System.out.println(verifier.verifyJson(filePath));
    }
}

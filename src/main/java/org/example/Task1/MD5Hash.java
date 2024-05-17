package org.example.Task1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Hash {

    public static void main(String[] args) {
        String inputFile = "src/main/java/org/example/Task1/input.txt";
        String outputFile = "src/main/java/org/example/Task1/output.txt";

        try {
            processInputFile(inputFile, outputFile);
            System.out.println("MD5 hashes successfully calculated and saved to output.txt");
        } catch (IOException | NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void processInputFile(String inputFile, String outputFile) throws IOException, NoSuchAlgorithmException {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             FileWriter writer = new FileWriter(outputFile)) {

            String line;
            while ((line = reader.readLine()) != null) {
                String md5Hash = calculateMD5(line);
                writer.write(line + ": " + md5Hash + "\n");
            }
        }
    }

    public static String calculateMD5(String input) throws NoSuchAlgorithmException {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }

        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());

        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }

        return hexString.toString();
    }
}

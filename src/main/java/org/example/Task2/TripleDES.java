package org.example.Task2;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class TripleDES {

    private static final String ALGORITHM = "DESede";

    public static byte[] encrypt(String plaintext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        return cipher.doFinal(plaintext.getBytes());
    }

    public static String decrypt(byte[] ciphertext, SecretKey secretKey) throws Exception {
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedBytes = cipher.doFinal(ciphertext);
        return new String(decryptedBytes);
    }

    public static SecretKey generateKey() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
        return keyGenerator.generateKey();
    }

    public static void main(String[] args) {
        try {
            SecretKey secretKey = generateKey();

            String inputFileName = "src/main/java/org/example/Task2/input.txt";
            byte[] inputData = Files.readAllBytes(Paths.get(inputFileName));

            String plaintext = new String(inputData);
            byte[] encryptedData = encrypt(plaintext, secretKey);

            String encryptedFileName = "src/main/java/org/example/Task2/encrypted.txt";
            FileOutputStream outputStream = new FileOutputStream(encryptedFileName);
            outputStream.write(encryptedData);
            outputStream.close();

            System.out.println("Encrypted data saved to " + encryptedFileName);
            byte[] encryptedFromFile = Files.readAllBytes(Paths.get(encryptedFileName));

            String decryptedData = decrypt(encryptedFromFile, secretKey);
            String decryptedFileName = "src/main/java/org/example/Task2/decrypted.txt";

            PrintWriter writer = new PrintWriter(decryptedFileName);
            writer.print(decryptedData);
            writer.close();
            System.out.println("Decrypted data saved to " + decryptedFileName);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | IOException | IllegalBlockSizeException | BadPaddingException | InvalidKeyException e) {
            System.err.println("Error: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


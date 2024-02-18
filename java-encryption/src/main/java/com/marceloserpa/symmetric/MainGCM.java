package com.marceloserpa.symmetric;


import javax.crypto.*;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class MainGCM {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String text = "Hello World From Marcelo";

        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(128);
        SecretKey secretKey = keygenerator.generateKey();

        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        GCMParameterSpec gcmParameterSpec = new GCMParameterSpec(128, initializationVector);

        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, gcmParameterSpec);
        byte[] cipherTextBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        System.out.println("Encrypted message= " + cipherTextBytes);

        Cipher cipherDecrypt = Cipher.getInstance("AES/GCM/NoPadding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec);
        String plainText = new String(cipherDecrypt.doFinal(cipherTextBytes));

        System.out.println("Dencrypted message= " + plainText);
    }
}
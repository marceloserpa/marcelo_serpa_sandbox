package com.marceloserpa.symmetric;


import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        String text = "Hello World From Marcelo";

        KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
        keygenerator.init(128);
        SecretKey secretKey = keygenerator.generateKey();

        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(initializationVector);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);

        Cipher cipher = Cipher.getInstance("AES/CFB8/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] cipherTextBytes = cipher.doFinal(text.getBytes(StandardCharsets.UTF_8));

        System.out.println("Encrypted message= " + cipherTextBytes);

        Cipher cipherDecrypt = Cipher.getInstance("AES/CFB8/NoPadding");
        cipherDecrypt.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        String plainText = new String(cipherDecrypt.doFinal(cipherTextBytes));

        System.out.println("Dencrypted message= " + plainText);
    }
}
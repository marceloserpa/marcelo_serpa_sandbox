package com.marceloserpa.symmetric;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.xml.bind.DatatypeConverter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // Generating shared key
        SecureRandom secureRandom = new SecureRandom();
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, secureRandom);
        SecretKey sharedKey = keyGenerator.generateKey();
        System.out.print("The Symmetric Key is :" + DatatypeConverter.printHexBinary(sharedKey.getEncoded()));

        // vector for encryption
        byte[] initializationVector = new byte[16];
        SecureRandom secureRandom2 = new SecureRandom();
        secureRandom2.nextBytes(initializationVector);

        String plainText = "MarceloP0C%#ç123";

        // Encryption
        Cipher cipherEncryption = Cipher.getInstance("AES/CBC/PKCS5PADDING");
        IvParameterSpec ivParameterSpec = new IvParameterSpec(initializationVector);
        cipherEncryption.init(Cipher.ENCRYPT_MODE, sharedKey, ivParameterSpec);
        byte[] cypherText = cipherEncryption.doFinal(plainText.getBytes());
        System.out.println("Cypher Text is: " + DatatypeConverter.printHexBinary(cypherText));

        // Decryption
        Cipher cipherDecryption = Cipher.getInstance( "AES/CBC/PKCS5PADDING");
        IvParameterSpec ivParameterSpecDecryption = new IvParameterSpec(initializationVector);
        cipherDecryption.init(Cipher.DECRYPT_MODE, sharedKey, ivParameterSpecDecryption);
        byte[] result = cipherDecryption.doFinal(cypherText);

        String decrypted = new String(result);
        System.out.println("Decrypted: " + decrypted);

    }

}
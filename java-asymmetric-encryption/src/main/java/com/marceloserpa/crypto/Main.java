package com.marceloserpa.crypto;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.xml.bind.DatatypeConverter;
import java.security.*;

public class Main {

    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        SecureRandom random = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048, random);

        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        System.out.println("Public Key: " + DatatypeConverter.printHexBinary(keyPair.getPublic().getEncoded()));
        System.out.println("Private Key: " + DatatypeConverter.printHexBinary(keyPair.getPrivate().getEncoded()));

        String password = "MarceloP0C%#ç123";

        // encryption
        Cipher encryptionCipher = Cipher.getInstance("RSA");
        encryptionCipher.init(Cipher.ENCRYPT_MODE, keyPair.getPrivate());
        byte[] cipherPassword = encryptionCipher.doFinal(password.getBytes());
        System.out.println("Cipher password: " + DatatypeConverter.printHexBinary(cipherPassword));

        // decryption
        Cipher decryptionCipher = Cipher.getInstance("RSA");
        decryptionCipher.init(Cipher.DECRYPT_MODE, keyPair.getPublic());
        byte[] result = decryptionCipher.doFinal(cipherPassword);
        String decryptedPassword =  new String(result);
        System.out.println("Decrypted password: " + decryptedPassword);


    }
}
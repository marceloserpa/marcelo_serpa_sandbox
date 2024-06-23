package com.mserpa.kmsfun;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;

import java.nio.charset.StandardCharsets;

public class KmsCrypto {


    public SdkBytes encrypt(KmsClient kmsClient, String keyId, String plainText){
        try {
            SdkBytes myBytes = SdkBytes.fromUtf8String(plainText);
            EncryptRequest encryptRequest = EncryptRequest.builder()
                    .keyId(keyId)
                    .plaintext(myBytes)
                    .encryptionAlgorithm(EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT)
                    .build();

            EncryptResponse response = kmsClient.encrypt(encryptRequest);
            String algorithm = response.encryptionAlgorithm().toString();
            System.out.println("The string was encrypted with algorithm " + algorithm + ".");

            //SdkBytes encryptedData = response.ciphertextBlob();

            //return Base64.getEncoder().encodeToString(encryptedData.asByteArray());
            return response.ciphertextBlob();

        } catch (KmsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(KmsClient kmsClient, String keyId, SdkBytes encryptedText){
        try {
            DecryptRequest decryptRequest = DecryptRequest.builder()
                    .ciphertextBlob(encryptedText)
                    .keyId(keyId)
                    .build();

            DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);
            return decryptResponse.plaintext().asString(StandardCharsets.UTF_8);

        } catch (KmsException e) {
            e.printStackTrace();
        }
        return "";


    }
}

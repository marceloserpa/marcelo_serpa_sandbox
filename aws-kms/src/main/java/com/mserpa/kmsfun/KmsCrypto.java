package com.mserpa.kmsfun;

import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.*;

import java.nio.charset.StandardCharsets;

public class KmsCrypto {

    public static final EncryptionAlgorithmSpec ENCRYPTION_ALGORITHM = EncryptionAlgorithmSpec.SYMMETRIC_DEFAULT;
    private KmsClient kmsClient;

    public KmsCrypto(KmsClient kmsClient) {
        this.kmsClient = kmsClient;
    }


    public SdkBytes encrypt(String keyId, String plainText){
        try {
            SdkBytes myBytes = SdkBytes.fromUtf8String(plainText);
            EncryptRequest encryptRequest = EncryptRequest.builder()
                    .keyId(keyId)
                    .plaintext(myBytes)
                    .encryptionAlgorithm(ENCRYPTION_ALGORITHM)
                    .build();

            EncryptResponse response = kmsClient.encrypt(encryptRequest);
            String algorithm = response.encryptionAlgorithm().toString();
            System.out.println("The string was encrypted with algorithm " + algorithm + ".");

            return response.ciphertextBlob();

        } catch (KmsException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String keyId, SdkBytes encryptedText){
        try {
            DecryptRequest decryptRequest = DecryptRequest.builder()
                    .ciphertextBlob(encryptedText)
                    .keyId(keyId)
                    .encryptionAlgorithm(ENCRYPTION_ALGORITHM)
                    .build();

            DecryptResponse decryptResponse = kmsClient.decrypt(decryptRequest);
            return decryptResponse.plaintext().asString(StandardCharsets.UTF_8);

        } catch (KmsException e) {
            e.printStackTrace();
        }
        return "";
    }

    public SdkBytes rotate(String originalKeyId, String newKeyId, SdkBytes encryptedText){
        var decoded = decrypt(originalKeyId, encryptedText);
        return encrypt(newKeyId, decoded);
    }
}

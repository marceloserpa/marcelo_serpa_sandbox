package com.mserpa.kmsfun;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;
import software.amazon.awssdk.services.kms.model.EncryptRequest;
import software.amazon.awssdk.services.kms.model.EncryptResponse;
import software.amazon.awssdk.services.kms.model.EncryptionAlgorithmSpec;
import software.amazon.awssdk.services.kms.model.KmsException;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Base64;

public class Main {

    private static final String ENDPOINT_URL = "http://localhost:4566";
    private static final Region DEFAULT_REGION = Region.US_EAST_1;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("KMS FUN");

        var sensibledata = "hello";
        var keyId = "arn:aws:kms:us-east-1:000000000000:key/4d80a39a-c33c-4edb-afad-c80b7c4955b0";

        AwsBasicCredentials credentials = AwsBasicCredentials.create("123456", "qwerty");
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        KmsClient kmsClient = KmsClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(DEFAULT_REGION)
                .endpointOverride(new URI(ENDPOINT_URL))
                .build();

        var encrypted = encrypt(kmsClient, keyId, sensibledata);


        System.out.println(encrypted);


        kmsClient.close();


    }

    public static String encrypt(KmsClient kmsClient, String keyId, String plainText){
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

            SdkBytes encryptedData = response.ciphertextBlob();

            return Base64.getEncoder().encodeToString(encryptedData.asByteArray());


        } catch (KmsException e) {
            e.printStackTrace();
            return null;
        }
    }


}
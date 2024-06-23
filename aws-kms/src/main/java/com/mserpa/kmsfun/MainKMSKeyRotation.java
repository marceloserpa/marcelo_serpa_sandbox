package com.mserpa.kmsfun;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.kms.KmsClient;

import java.net.URI;
import java.net.URISyntaxException;

public class MainKMSKeyRotation {

    private static final String ENDPOINT_URL = "http://localhost:4566";
    private static final Region DEFAULT_REGION = Region.US_EAST_1;

    public static void main(String[] args) throws URISyntaxException {
        System.out.println("KMS FUN");

        var sensibledata = "hello";
        var originalKeyId = "arn:aws:kms:us-east-1:000000000000:key/4d80a39a-c33c-4edb-afad-c80b7c4955b0";
        var newKeyId      = "arn:aws:kms:us-east-1:000000000000:key/d0b7ec1c-a3c1-4c4b-8cbe-b385ad84a9a3";

        AwsBasicCredentials credentials = AwsBasicCredentials.create("123456", "qwerty");
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        KmsClient kmsClient = KmsClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(DEFAULT_REGION)
                .endpointOverride(new URI(ENDPOINT_URL))
                .build();

        var kmsCrypto = new KmsCrypto(kmsClient);

        var encrypted = kmsCrypto.encrypt(originalKeyId, sensibledata);

        System.out.println("ENCRYPTED: " + encrypted);

        var decrypted = kmsCrypto.decrypt(newKeyId, encrypted);

        System.out.println("DECRYPTED: " + decrypted);

        System.out.println("ROTATE");

        var encryptedRotated = kmsCrypto.rotate(originalKeyId, newKeyId, encrypted);
        decrypted = kmsCrypto.decrypt(newKeyId, encryptedRotated);

        System.out.println("DECRYPTED: " + decrypted);

        kmsClient.close();


    }



}
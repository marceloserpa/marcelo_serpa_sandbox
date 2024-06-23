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
        var keyId = "arn:aws:kms:us-east-1:000000000000:key/4d80a39a-c33c-4edb-afad-c80b7c4955b0";

        AwsBasicCredentials credentials = AwsBasicCredentials.create("123456", "qwerty");
        StaticCredentialsProvider credentialsProvider = StaticCredentialsProvider.create(credentials);

        KmsClient kmsClient = KmsClient.builder()
                .credentialsProvider(credentialsProvider)
                .region(DEFAULT_REGION)
                .endpointOverride(new URI(ENDPOINT_URL))
                .build();

        var kmsCrypto = new KmsCrypto(kmsClient);

        var encrypted = kmsCrypto.encrypt(keyId, sensibledata);

        System.out.println("ENCRYPTED: " + encrypted);

        var decrypted = kmsCrypto.decrypt(keyId, encrypted);

        System.out.println("DECRYPTED: " + decrypted);

        System.out.println(encrypted);


        kmsClient.close();


    }



}
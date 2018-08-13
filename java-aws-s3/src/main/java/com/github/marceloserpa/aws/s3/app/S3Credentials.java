package com.github.marceloserpa.aws.s3.app;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Credentials {
	
	public static AmazonS3 getClient() {
		String accessKey = System.getenv("access_key_id");
		String secretKey = System.getenv("secret_key_id");
		
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(accessKey, secretKey);
		return AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCreds))
            .build();
	}
	

}

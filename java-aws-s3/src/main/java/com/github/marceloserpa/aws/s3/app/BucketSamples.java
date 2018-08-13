package com.github.marceloserpa.aws.s3.app;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.Bucket;

public class BucketSamples {

	public static void main(String[] args) {
		
		String bucketName = "marcelo-my-first-bucket";		
		AmazonS3 s3Client = S3Credentials.getClient();
		
		if(!s3Client.doesBucketExistV2(bucketName)) {
			System.out.println("Creating bucket " + bucketName + " !!");
			s3Client.createBucket(bucketName);
			System.out.println("Bucket " + bucketName + " created!");
		} else {
			System.out.println("Bucket "+ bucketName +" already exists");
		}
		
		System.out.println("List buckets");
		for (Bucket bucket : s3Client.listBuckets()) {
		    System.out.println("* " + bucket.getName());
		}
		
		System.out.println("Delete bucket: ");
		s3Client.deleteBucket(bucketName);
		
		System.out.println("List buckets");
		for (Bucket bucket : s3Client.listBuckets()) {
		    System.out.println("* " + bucket.getName());
		}
		
	}
	
}

package com.marceloserpa.lambdas3.poc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.event.S3EventNotification.S3EventNotificationRecord;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class ReaderHandler implements RequestHandler<S3Event, String> {

  private static final Logger logger = LoggerFactory.getLogger(ReaderHandler.class);

  private Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Override
  public String handleRequest(S3Event s3event, Context context) {
    try {
      logger.info("EVENT: " + gson.toJson(s3event));
      S3EventNotificationRecord record = s3event.getRecords().get(0);
      String srcBucket = record.getS3().getBucket().getName();
      String srcKey = record.getS3().getObject().getUrlDecodedKey();
      logger.info("BUCKET: {}", srcBucket);
      logger.info("KEY: {}", srcKey);
      
      Matcher matcher = Pattern.compile(".*\\.([^\\.]*)").matcher(srcKey);
      if (!matcher.matches()) {
        logger.info("Unable to infer image type for key " + srcKey);
        return "FAIL";
      }

      AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
      S3Object s3Object = s3Client.getObject(new GetObjectRequest(srcBucket, srcKey));
      InputStream objectData = s3Object.getObjectContent();

      BufferedReader reader = new BufferedReader(new InputStreamReader(objectData));
      String line = null;
      StringBuilder builder = new StringBuilder("BACKUP CONTENT **** \n\n\n\n");
      while ((line = reader.readLine()) != null) {
        builder.append(line);
      }

      logger.info("content file: {}", builder.toString());
      return "Ok";
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

package com.marceloserpa.sparkes;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.elasticsearch.spark.rdd.api.java.JavaEsSpark;

import java.util.Date;

public class Application {

    public static void main(String[] args) {

        SparkConf conf = new SparkConf()
                .setMaster("local[*]")
                .setAppName("SparkFileSumApp")
                .set("es.index.auto.create", "true");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);

        JavaRDD<String> csvContent = sparkContext.textFile("datasets/ml-latest-small/ratings.csv");

        String header = csvContent.first();

        JavaRDD<Rating> ratings = csvContent.filter(row -> !header.equals(row)).map(mapToRating());

        JavaEsSpark.saveToEs(ratings, "movies-rating");
    }

    private static Function<String, Rating> mapToRating() {
        return line -> {
            String[] splitted = line.split(",");
            System.out.println("0=" + splitted[0] + " 1=" + splitted[1] + " 2=" + splitted[2]);

            Integer userId = Integer.valueOf(splitted[0]);
            Integer movieId = Integer.valueOf(splitted[1]);
            Double rating = Double.valueOf(splitted[2]);

            Date date = new Date(Long.valueOf(splitted[3]));
            return new Rating(userId, movieId, rating, date);
        };
    }

}

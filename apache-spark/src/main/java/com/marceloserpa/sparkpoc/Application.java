package com.marceloserpa.sparkpoc;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import scala.Tuple2;

public class Application {

	public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkFileSumApp");
        JavaSparkContext sc = new JavaSparkContext(conf);
        
        JavaRDD<Sale> lines = sc.textFile("report/sales_report_2019_11_17.txt")
        		.map(parseToSale());
        
		JavaPairRDD<Integer, Iterable<Double>> groupByKey = lines.mapToPair(sale -> {
			Tuple2<Integer, Double> tuple2 = new Tuple2<>(sale.getIdSalesman(), sale.getPrice());
			return tuple2;
		}).distinct().groupByKey();
        
        sc.close();
	}

	private static Function<String, Sale> parseToSale() {
		return line -> {
        	String[] splitted = line.split(";");
        	return new Sale(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]), Double.valueOf(splitted[2]));
        };
	}
	
}

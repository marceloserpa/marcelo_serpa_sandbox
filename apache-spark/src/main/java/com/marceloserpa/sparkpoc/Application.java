package com.marceloserpa.sparkpoc;

import java.util.List;
import java.util.stream.StreamSupport;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

import scala.Tuple2;

public class Application {

	public static void main(String[] args) {
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkFileSumApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);
        
        JavaRDD<Sale> sales = sparkContext.textFile("report/sales_report_2019_11_17.txt")
        		.map(parseToSale());
        
		JavaPairRDD<Integer, Iterable<Double>> pairs = sales.mapToPair(sale -> {
			Tuple2<Integer, Double> tuple2 = new Tuple2<>(sale.getIdSalesman(), sale.getPrice());
			return tuple2;
		}).distinct().groupByKey();
        
		List<Tuple2<Integer, Double>> summary = pairs.aggregateByKey(
				0D, 
				(acc, salesBySalesman) -> acc + StreamSupport.stream(salesBySalesman.spliterator(), false).reduce((sale1, sale2) -> sale1 + sale2).get(), 
				(acc1, acc2) -> acc1 + acc2
			).collect();
		
		System.out.println("*** SUMMARY ***\n\n");
		for(Tuple2<Integer, Double> result : summary) {
			System.out.println("SaleManID: " + result._1() + " Total: " + result._2());
		}
		
        sparkContext.close();
	}

	private static Function<String, Sale> parseToSale() {
		return line -> {
        	String[] splitted = line.split(";");
        	System.out.println("0=" + splitted[0] + " 1=" + splitted[1] + " 2"+splitted[2]);
        	return new Sale(Integer.valueOf(splitted[0]), Integer.valueOf(splitted[1]), Double.valueOf(splitted[2]));
        };
	}
	
}

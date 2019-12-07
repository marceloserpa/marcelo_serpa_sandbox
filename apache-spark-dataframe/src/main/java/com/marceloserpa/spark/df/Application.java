package com.marceloserpa.spark.df;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.AnalysisException;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import static org.apache.spark.sql.functions.col;

public class Application {

	public static void main(String[] args) throws AnalysisException {
		
        SparkConf conf = new SparkConf().setMaster("local[*]").setAppName("SparkFileSumApp");
        JavaSparkContext sparkContext = new JavaSparkContext(conf);		
        SQLContext sqlContext = new SQLContext(sparkContext);
        
		Dataset<Row> df = sqlContext.read().json("report/people.json");

		// Displays the content of the DataFrame to stdout
		df.show();
		/*
		+---------------+---+--------------------+
		|         author| id|               title|
		+---------------+---+--------------------+
		|   Stephen King|  1|         The Shining|
		|H. P. Lovecraft|  2|The Whisperer in ...|
		|   Stephan King|  3|                  IT|
		|H. P. Lovecraft|  4|  The Dunwich Horror|
		|   Stephen King|  5|     Under the Domme|
		+---------------+---+--------------------+
		 */
		
		df.select(col("title"), col("author")).where(col("author").like("%Stephen%")).show();
		/*
		+---------------+------------+
		|          title|      author|
		+---------------+------------+
		|    The Shining|Stephen King|
		|Under the Domme|Stephen King|
		+---------------+------------+
		 */
		
		// Register the DataFrame as a global temporary view
		df.createGlobalTempView("books");
		
		sqlContext.sql("select * from global_temp.books where author like '%H.%'").show();
		/*
		+---------------+---+--------------------+
		|         author| id|               title|
		+---------------+---+--------------------+
		|H. P. Lovecraft|  2|The Whisperer in ...|
		|H. P. Lovecraft|  4|  The Dunwich Horror|
		+---------------+---+--------------------+
		*/
	}
	
}

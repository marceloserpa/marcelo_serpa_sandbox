package marceloserpa;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.setParallelism(1);

		DataStream<String> text = env.fromElements(
				"hello flink",
				"hello world",
				"flink is powerful"
		);

		DataStream<Tuple2<String, Integer>> counts =
				text
						.flatMap(new Tokenizer())
						.keyBy(value -> value.f0)
						.sum(1);

		counts.print();

		env.execute("flink hello world count..");

	}

	public static class Tokenizer implements FlatMapFunction<String, Tuple2<String, Integer>> {

		@Override
		public void flatMap(String value,
							Collector<Tuple2<String, Integer>> out) {

			String[] words = value.toLowerCase().split("\\W+");

			for (String word : words) {
				if (word.length() > 0) {
					out.collect(new Tuple2<>(word, 1));
				}
			}
		}
	}
}

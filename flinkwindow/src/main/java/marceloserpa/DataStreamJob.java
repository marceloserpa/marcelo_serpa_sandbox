package marceloserpa;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.TumblingProcessingTimeWindows;

import java.time.Duration;

public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		StreamExecutionEnvironment env =
				StreamExecutionEnvironment.getExecutionEnvironment();

		env.setParallelism(1);

		DataStream<String> input =
				env.socketTextStream("localhost", 9999);

		DataStream<ProductViewEvent> events =
				input.map(new MapFunction<String, ProductViewEvent>() {

					@Override
					public ProductViewEvent map(String value) {
						return new ProductViewEvent(
								value
						);
					}
				});

		events
				.keyBy(ProductViewEvent::getProductId)
				.window(TumblingProcessingTimeWindows.of(Duration.ofSeconds(10)))
				.process(new ViewCounter())
				.print();

		env.execute("Product Visualization Counter");
	}
}

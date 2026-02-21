package marceloserpa;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.setParallelism(1);

		DataStream<Vote> votes = env.fromElements(
				new Vote("v1", "A"),
				new Vote("v2", "A"),
				new Vote("v1", "B"),  // duplicate voter
				new Vote("v3", "B")
		);

		DataStream<Vote> uniqueVotes =
					votes
						.keyBy(vote -> vote.getUserId())
						.process(new DeduplicateVotes());
		uniqueVotes
				.map(vote -> Tuple2.of(vote.getCandidateId(), 1))
				.returns(Types.TUPLE(Types.STRING, Types.INT))
				.keyBy(t -> t.f0)
				.sum(1)
				.print();

		env.execute("Vote Count with Deduplication");
	}
}

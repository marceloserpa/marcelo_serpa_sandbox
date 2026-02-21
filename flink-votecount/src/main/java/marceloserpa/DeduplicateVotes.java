package marceloserpa;

import org.apache.flink.api.common.functions.OpenContext;
import org.apache.flink.api.common.state.ValueState;
import org.apache.flink.api.common.state.ValueStateDescriptor;
import org.apache.flink.streaming.api.functions.KeyedProcessFunction;
import org.apache.flink.util.Collector;


public class DeduplicateVotes
        extends KeyedProcessFunction<String, Vote, Vote> {

    private ValueState<Boolean> hasVoted;

    @Override
    public void open(OpenContext openContext) throws Exception {
        ValueStateDescriptor<Boolean> descriptor =
                new ValueStateDescriptor<>("hasVoted", Boolean.class);
        hasVoted = getRuntimeContext().getState(descriptor);
    }

    @Override
    public void processElement(
            Vote vote,
            Context ctx,
            Collector<Vote> out) throws Exception {

        if (hasVoted.value() == null) {
            hasVoted.update(true);
            out.collect(vote);
        }
        // else ignore duplicate
    }
}
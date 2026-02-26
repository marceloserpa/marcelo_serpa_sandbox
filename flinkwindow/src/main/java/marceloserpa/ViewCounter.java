package marceloserpa;

import org.apache.flink.streaming.api.functions.windowing.ProcessWindowFunction;
import org.apache.flink.streaming.api.windowing.windows.TimeWindow;
import org.apache.flink.util.Collector;

public class ViewCounter
        extends ProcessWindowFunction<ProductViewEvent, String, String, TimeWindow> {

    @Override
    public void process(
            String key,
            Context context,
            Iterable<ProductViewEvent> events,
            Collector<String> out) {

        int count = 0;

        for (ProductViewEvent e : events) {
            count++;
        }

        out.collect(
                "Product: " + key +
                        " Views: " + count +
                        " WindowStart: " + context.window().getStart()
        );
    }
}
package marceloserpa;

import org.apache.flink.api.common.state.BroadcastState;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SaleSalesmanBroadcastProcess extends KeyedBroadcastProcessFunction<Integer, Sale, Salesman, Tuple2<Sale, Salesman>> {

    private final MapStateDescriptor<Integer, Salesman> salesmanStateDescriptor;

    private final Map<Integer, List<Sale>> pendingSales = new HashMap<>();

    public SaleSalesmanBroadcastProcess(MapStateDescriptor<Integer, Salesman> salesmanStateDescriptor) {
        this.salesmanStateDescriptor = salesmanStateDescriptor;
    }

    @Override
    public void processElement(Sale sale, ReadOnlyContext ctx, Collector<Tuple2<Sale, Salesman>> out) throws Exception {
        ReadOnlyBroadcastState<Integer, Salesman> salesmanState = ctx.getBroadcastState(salesmanStateDescriptor);
        Salesman salesman = salesmanState.get(sale.getSalesman_id());

        if (salesman != null) {
            out.collect(Tuple2.of(sale, salesman));
        } else {
            pendingSales.computeIfAbsent(sale.getSalesman_id(), k -> new ArrayList<>()).add(sale);
        }
    }

    @Override
    public void processBroadcastElement(Salesman salesman, Context ctx, Collector<Tuple2<Sale, Salesman>> out) throws Exception {
        BroadcastState<Integer, Salesman> broadcastState = ctx.getBroadcastState(salesmanStateDescriptor);
        broadcastState.put(salesman.getId(), salesman);

        List<Sale> pending = pendingSales.remove(salesman.getId());
        if (pending != null) {
            for (Sale sale : pending) {
                out.collect(Tuple2.of(sale, salesman));
            }
        }
    }
}
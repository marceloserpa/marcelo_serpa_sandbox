package marceloserpa;

import org.apache.flink.api.common.state.ListState;
import org.apache.flink.api.common.state.ListStateDescriptor;
import org.apache.flink.api.common.state.MapStateDescriptor;
import org.apache.flink.api.common.state.ReadOnlyBroadcastState;
import org.apache.flink.api.common.typeinfo.TypeHint;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.functions.co.KeyedBroadcastProcessFunction;
import org.apache.flink.util.Collector;

import java.util.ArrayList;
import java.util.List;

public class SaleStoreBroadcastProcess extends KeyedBroadcastProcessFunction<Integer, Tuple2<Sale, Salesman>, Store, SaleEnriched> {

    private final MapStateDescriptor<Integer, Store> storeStateDescriptor;

    private transient ListState<Tuple2<Sale, Salesman>> pendingSales;

    public SaleStoreBroadcastProcess(MapStateDescriptor<Integer, Store> storeStateDescriptor) {
        this.storeStateDescriptor = storeStateDescriptor;
    }

    @Override
    public void open(Configuration parameters) throws Exception {
        pendingSales = getRuntimeContext().getListState(
                new ListStateDescriptor<>(
                        "pendingSalesStore",
                        TypeInformation.of(new TypeHint<Tuple2<Sale, Salesman>>() {})
                )
        );
    }

    @Override
    public void processElement(Tuple2<Sale, Salesman> value, ReadOnlyContext ctx, Collector<SaleEnriched> out) throws Exception {
        ReadOnlyBroadcastState<Integer, Store> storeState = ctx.getBroadcastState(storeStateDescriptor);
        Store store = storeState.get(value.f1.getStore_id());

        if (store != null) {
            collectEnriched(value, store, out);
        } else {
            pendingSales.add(value);
        }
        processPending(storeState, out);
    }

    @Override
    public void processBroadcastElement(Store store, Context ctx, Collector<SaleEnriched> out) throws Exception {
        ctx.getBroadcastState(storeStateDescriptor).put(store.getId(), store);
    }

    private void processPending(ReadOnlyBroadcastState<Integer, Store> storeState, Collector<SaleEnriched> out) throws Exception {
        Iterable<Tuple2<Sale, Salesman>> pends = pendingSales.get();
        if (pends == null) return;

        List<Tuple2<Sale, Salesman>> stillPending = new ArrayList<>();

        for (Tuple2<Sale, Salesman> pending : pends) {
            Store store = storeState.get(pending.f1.getStore_id());
            if (store != null) {
                collectEnriched(pending, store, out);
            } else {
                stillPending.add(pending);
            }
        }

        if (stillPending.isEmpty()) {
            pendingSales.clear();
        } else {
            pendingSales.update(stillPending);
        }
    }

    private void collectEnriched(Tuple2<Sale, Salesman> value, Store store, Collector<SaleEnriched> out) {
        Sale sale = value.f0;
        Salesman salesman = value.f1;

        SaleEnriched enriched = new SaleEnriched();
        enriched.setSalesman_id(salesman.getId());
        enriched.setSalesman_name(salesman.getName());
        enriched.setStore_name(store.getName());
        enriched.setCity(store.getCity());
        enriched.setState(store.getState());
        enriched.setTotal(sale.getTotal());

        out.collect(enriched);
    }
}
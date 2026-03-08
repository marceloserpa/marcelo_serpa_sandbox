package marceloserpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.functions.MapFunction;

public class StoreDeserializer implements MapFunction<String, Store> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Store map(String value) throws Exception {
        return mapper.readValue(value, Store.class);
    }
}

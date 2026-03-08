package marceloserpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.functions.MapFunction;

public class SaleDeserializer implements MapFunction<String, Sale> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Sale map(String value) throws Exception {
        return mapper.readValue(value, Sale.class);
    }
}

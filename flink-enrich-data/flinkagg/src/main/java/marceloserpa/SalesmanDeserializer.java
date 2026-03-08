package marceloserpa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.flink.api.common.functions.MapFunction;

public class SalesmanDeserializer implements MapFunction<String, Salesman> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Salesman map(String value) throws Exception {
        return mapper.readValue(value, Salesman.class);
    }
}

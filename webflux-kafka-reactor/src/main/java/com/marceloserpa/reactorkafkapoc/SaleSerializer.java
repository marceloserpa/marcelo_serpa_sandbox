package com.marceloserpa.reactorkafkapoc;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;


public class SaleSerializer implements Serializer<Sale> {

    @Override
    public byte[] serialize(String topic, Sale data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return retVal;
    }

}

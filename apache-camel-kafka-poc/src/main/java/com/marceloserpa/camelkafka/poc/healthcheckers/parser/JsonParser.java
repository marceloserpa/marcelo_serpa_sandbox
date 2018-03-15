package com.marceloserpa.camelkafka.poc.healthcheckers.parser;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.marceloserpa.camelkafka.poc.healthcheckers.models.Health;

import java.io.IOException;

public class JsonParser {

    public String parse(Health health){
        ObjectMapper mapper = new ObjectMapper().setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String json = "";
        try {
            json = ow.writeValueAsString(health).replace("\n", "").replace(" ", "");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json;
    }

}

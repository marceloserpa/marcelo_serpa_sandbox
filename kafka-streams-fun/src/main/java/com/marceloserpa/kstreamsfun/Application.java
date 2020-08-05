package com.marceloserpa.kstreamsfun;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.SessionWindows;
import org.apache.kafka.streams.kstream.TimeWindows;

public class Application {

  public static void main(String[] args) {

    Properties props = new Properties();
    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "my-stream-processing-application-8888");
    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

    StreamsBuilder builder = new StreamsBuilder();
    builder.stream("inputs", Consumed.with(Serdes.String(), Serdes.String()))
    
    	.map((k, v) -> KeyValue.pair("all", v))
    
    	.groupByKey().windowedBy(TimeWindows.of(Duration.ofSeconds(10)))
    	
    	.reduce((a, b) -> a + b)
    	
    	.toStream()
    	
        .map((key, sum) -> KeyValue.pair(key.key(), sum.toString()))

    	.to("results", Produced.with(Serdes.String(), Serdes.String()));


    KafkaStreams streams = new KafkaStreams(builder.build(), props);
    streams.start();
  }
}

package com.ms.ribbonpoc;

import com.google.common.collect.ImmutableList;
import com.netflix.ribbon.ClientOptions;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.http.HttpRequestTemplate;
import com.netflix.ribbon.http.HttpResourceGroup;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.List;

public class HelloWorldRibbon {

    private final static String LIST_OF_HOSTS = "localhost:9000, localhost:9001";

    public static void main(String args[]){

        HttpResourceGroup httpResourceGroup = Ribbon.createHttpResourceGroup("pingServiceClient",
                ClientOptions.create()
                        .withMaxAutoRetriesNextServer(3)
                        .withConfigurationBasedServerList(LIST_OF_HOSTS));

        HttpRequestTemplate<ByteBuf> pingIdTemplate = httpResourceGroup.newTemplateBuilder("pingIdTemplate", ByteBuf.class)
                .withMethod("GET")
                .withUriTemplate("/ping/{numberToPing}")
                .build();

        List<Integer> numbers = ImmutableList.of(0,1,2,3,4,5,6,7,8,9,10);

        numbers.stream().map(number -> {
            ByteBuf numberPrinted = pingIdTemplate.requestBuilder()
                    .withRequestProperty("numberToPing", number)
                    .build()
                    .execute();

            return numberPrinted.toString(Charset.defaultCharset());
        }).forEach(System.out::println);

        System.out.println("end..");
    }

}

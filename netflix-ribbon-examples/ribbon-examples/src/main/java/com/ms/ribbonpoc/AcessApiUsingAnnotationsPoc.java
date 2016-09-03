package com.ms.ribbonpoc;

import com.google.common.collect.ImmutableList;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.config.ConfigurationManager;
import com.netflix.ribbon.Ribbon;
import com.netflix.ribbon.RibbonRequest;
import com.netflix.ribbon.proxy.annotation.Http;
import com.netflix.ribbon.proxy.annotation.Var;
import io.netty.buffer.ByteBuf;

import java.nio.charset.Charset;
import java.util.List;

public class AcessApiUsingAnnotationsPoc {

    public static void main(String args[]){

        ConfigurationManager.getConfigInstance()
                .setProperty("PingRequestTemplate.ribbon." + CommonClientConfigKey.ListOfServers, "localhost:9000,localhost:9001");

        PingRequestTemplate service = Ribbon.from(PingRequestTemplate.class);

        List<Integer> numbers = ImmutableList.of(0,1,2,3,4,5,6,7,8,9,10);

        numbers.stream()
                .map(number -> {
                    ByteBuf numberPrinted = service.pingNumber(number).execute();
                    return numberPrinted.toString(Charset.defaultCharset());
                }).forEach(System.out::println);

    }

}

interface PingRequestTemplate {
    @Http(
            method = Http.HttpMethod.GET,
            uri = "/ping/{numberToPing}"
    )
    RibbonRequest<ByteBuf> pingNumber(@Var("numberToPing") Integer numberToPing);
}

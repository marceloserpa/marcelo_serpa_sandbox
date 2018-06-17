package com.marceloserpa.karyon.rxnetty.app;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.reactivex.netty.protocol.http.server.HttpServerRequest;
import io.reactivex.netty.protocol.http.server.HttpServerResponse;
import io.reactivex.netty.protocol.http.server.RequestHandler;
import netflix.karyon.transport.http.health.HealthCheckEndpoint;
import org.codehaus.jackson.map.ObjectMapper;
import rx.Observable;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class RxNettyHandler implements RequestHandler<ByteBuf, ByteBuf> {

    private HealthCheckEndpoint healthCheckEndpoint = new HealthCheckEndpoint(new HealthChecker());
    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Book> books = new ArrayList<>();


    @Override
    public Observable<Void> handle(HttpServerRequest<ByteBuf> request, HttpServerResponse<ByteBuf> response) {

        if (request.getUri().startsWith("/healthcheck")) {
            return healthCheckEndpoint.handle(request, response);
        } else if(request.getUri().startsWith("/books") && request.getHttpMethod().equals(HttpMethod.POST)){
            return request.getContent()
                    .map(byteBuf -> byteBuf.toString(Charset.forName("UTF-8")))
                    .map(json -> {
                        try {
                            return objectMapper.readValue(json, Book.class);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }).flatMap(book -> {
                        try {
                            books.add(book);
                            return response.writeStringAndFlush(objectMapper.writeValueAsString(book));
                        } catch (Exception e) {
                            response.setStatus(HttpResponseStatus.BAD_REQUEST);
                            return response.close();
                        }
                    });

        }  else if(request.getUri().startsWith("/books") && request.getHttpMethod().equals(HttpMethod.GET)){

            try {
                return response.writeStringAndFlush(objectMapper.writeValueAsString(books));
            } catch (IOException e) {
                e.printStackTrace();
            }
            response.setStatus(HttpResponseStatus.INTERNAL_SERVER_ERROR);
            return response.close();
        } else {
            response.setStatus(HttpResponseStatus.NOT_FOUND);
            return response.close();
        }

    }

}

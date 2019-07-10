package com.marceloserpa.netty.poc;

import static io.netty.handler.codec.http.HttpHeaderNames.CONNECTION;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_LENGTH;
import static io.netty.handler.codec.http.HttpHeaderNames.CONTENT_TYPE;
import static io.netty.handler.codec.http.HttpHeaderValues.CLOSE;
import static io.netty.handler.codec.http.HttpHeaderValues.KEEP_ALIVE;
import static io.netty.handler.codec.http.HttpHeaderValues.TEXT_PLAIN;
import static io.netty.handler.codec.http.HttpHeaderValues.APPLICATION_JSON;
import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpResponseStatus.NOT_FOUND;

import java.nio.charset.StandardCharsets;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpUtil;

public class HttpEndpointHandler extends SimpleChannelInboundHandler<FullHttpRequest> {
	
	private static ObjectMapper objectMapper = new ObjectMapper();
	
	@Override
	public void channelRead0(ChannelHandlerContext context, FullHttpRequest fullHttpRequest) throws Exception {
        if (fullHttpRequest instanceof FullHttpRequest) {
            
        	FullHttpResponse response = null; 
            System.out.println(fullHttpRequest.uri());
            System.out.println(fullHttpRequest.method());
        	
        	if(HttpMethod.POST.equals(fullHttpRequest.method()) && "/notification-service/send".equals(fullHttpRequest.uri())) {
        		System.out.println("uri found");
                ByteBuf content = fullHttpRequest.content();
                
                String jsonRequest = content.toString(StandardCharsets.UTF_8);
                System.out.println(jsonRequest);
    			Notification notification = objectMapper.readValue(jsonRequest, Notification.class);            
                
                String json = objectMapper.writeValueAsString(notification);
                
                response = new DefaultFullHttpResponse(fullHttpRequest.protocolVersion(), OK, Unpooled.wrappedBuffer(json.getBytes()));
                response.headers()
                        .set(CONTENT_TYPE, APPLICATION_JSON)
                        .setInt(CONTENT_LENGTH, response.content().readableBytes());
        	}
        	
        	if(response == null) {
        		response = new DefaultFullHttpResponse(fullHttpRequest.protocolVersion(), NOT_FOUND, Unpooled.wrappedBuffer("url not found".getBytes()));
                response.headers()
                        .set(CONTENT_TYPE, TEXT_PLAIN)
                        .setInt(CONTENT_LENGTH, response.content().readableBytes());
        	}

            boolean keepAlive = HttpUtil.isKeepAlive(fullHttpRequest);
            if (keepAlive) {
                if (!fullHttpRequest.protocolVersion().isKeepAliveDefault()) {
                    response.headers().set(CONNECTION, KEEP_ALIVE);
                }
            } else {
                response.headers().set(CONNECTION, CLOSE);
            }

            ChannelFuture channelFuture = context.write(response);

            if (!keepAlive) {
                channelFuture.addListener(ChannelFutureListener.CLOSE);
            }
        }	
	}

    @Override
    public void channelReadComplete(ChannelHandlerContext context) {
        context.flush();
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
        cause.printStackTrace();
        context.close();
    }	
	
}

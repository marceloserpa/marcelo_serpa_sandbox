package com.marceloserpa.netty.poc;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class HttpEndpointInitializer extends ChannelInitializer<SocketChannel>{

    @Override
    public void initChannel(SocketChannel channel) throws Exception {
		ChannelPipeline pipeline = channel.pipeline();
		pipeline.addLast(new HttpRequestDecoder());
		pipeline.addLast(new HttpObjectAggregator(1048576));
		pipeline.addLast(new HttpResponseEncoder());
		pipeline.addLast(new HttpEndpointHandler());
    }
}

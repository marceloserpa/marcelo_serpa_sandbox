package com.marceloserpa.netty.poc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Application {

	private final static int PORT = 8080;

	public static void main(String[] args) throws InterruptedException {        
		EventLoopGroup bossGroup = new NioEventLoopGroup();
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    try {
	        ServerBootstrap server = new ServerBootstrap(); 
	        server.group(bossGroup, workerGroup)
	         .channel(NioServerSocketChannel.class) 
	         .childHandler(new HttpEndpointInitializer())
	         
	         .option(ChannelOption.SO_BACKLOG, 128)        
	         .childOption(ChannelOption.SO_KEEPALIVE, true); 
	
	        ChannelFuture channelFuture = server.bind(PORT).sync(); 
	
	        channelFuture.channel().closeFuture().sync();
	    } finally {
	        workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
	    }	
		
	}
	
	
}

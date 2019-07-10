package com.marceloserpa.nettypoc;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;

public class Application {

	public static void main(String[] args) throws InterruptedException {        
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
	    EventLoopGroup workerGroup = new NioEventLoopGroup();
	    try {
	        ServerBootstrap b = new ServerBootstrap(); // (2)
	        b.group(bossGroup, workerGroup)
	         .channel(NioServerSocketChannel.class) // (3)
	         .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
	             @Override
	             public void initChannel(SocketChannel ch) throws Exception {
	            	 
	            	 ch.pipeline().addLast(new HttpServerCodec());
	            	 ch.pipeline().addLast(new HttpServerExpectContinueHandler());	               
	                 ch.pipeline().addLast(new HttpHelloWorldServerHandler());
	             }
	         })
	         .option(ChannelOption.SO_BACKLOG, 128)          // (5)
	         .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
	
	        // Bind and start to accept incoming connections.
	        ChannelFuture f = b.bind(8080).sync(); // (7)
	
	        // Wait until the server socket is closed.
	        // In this example, this does not happen, but you can do that to gracefully
	        // shut down your server.
	        f.channel().closeFuture().sync();
	    } finally {
	        workerGroup.shutdownGracefully();
	        bossGroup.shutdownGracefully();
	    }	
		
	}
	
}

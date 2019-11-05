package cn.quyf.demo.netty.diamond.server;


import org.apache.log4j.Logger;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class DiamondServer {

	private static final Logger logger = Logger.getLogger(DiamondServer.class);
	
	public static void main(String[] args) throws Exception {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		ServerBootstrap strap = new ServerBootstrap();
		strap.group(boss, worker)
		.channel(NioServerSocketChannel.class)
		.childOption(ChannelOption.SO_BACKLOG, 1024)
		.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new DiamondServerHandler());
			}
		});
		
		ChannelFuture future = strap.bind( 8080).sync();
		future.channel().close().sync();
		System.out.println("启动 Diamond Netty Server, post=8001");
		
//		boss.shutdownGracefully();
//		worker.shutdownGracefully();
	}
}

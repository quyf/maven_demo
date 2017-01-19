package cn.quyf.demo.netty.protocol.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class PersonServer {

	public static void main(String[] args) {
		PersonServer server = new PersonServer();
		server.start( 8000 );
	}
	
	public void start(int port){
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		
		ServerBootstrap s = new ServerBootstrap();
		s.group(boss, worker).channel(NioServerSocketChannel.class);
		s.childOption( ChannelOption.SO_KEEPALIVE, true)
		.childOption( ChannelOption.SO_BACKLOG, 128)
		.childHandler( new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pp = ch.pipeline();
				pp.addLast( new PersonDecoder());
				pp.addLast( new ServerHandler());
			}
			
		});
		try {
			ChannelFuture f = s.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
		
	}
}

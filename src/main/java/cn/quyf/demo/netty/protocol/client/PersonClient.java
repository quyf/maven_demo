package cn.quyf.demo.netty.protocol.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PersonClient {

	public static void main(String[] args) {
		PersonClient client = new PersonClient();
		client.connect( "localhost",8080 );
	}
	public void connect(String host,int port){
		EventLoopGroup worker = new NioEventLoopGroup();
		Bootstrap s = new Bootstrap();
		s.group(worker).channel( NioSocketChannel.class )
		.option(ChannelOption.SO_KEEPALIVE, true);
		s.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pp = ch.pipeline();
				pp.addLast( new PersonEncoder());
				pp.addLast( new ClientHandler());
			}
			
		});
		try {
			ChannelFuture f = s.connect(host, port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			worker.shutdownGracefully();
		}
	}
}

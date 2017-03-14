package cn.quyf.demo.netty.mutilprotocol.client;

import cn.quyf.demo.netty.mutilprotocol.Person;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class PersonClient2 {

	public static void main(String[] args) {
		PersonClient2 client = new PersonClient2();
		client.connect( "localhost",8000 );
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
				pp.addLast( new StringEncoder());
				Person person = new Person();
				person.setName("guowl");
				person.setSex("man");
				person.setAge(30);
				pp.addLast( new ClientHandler( person ));
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

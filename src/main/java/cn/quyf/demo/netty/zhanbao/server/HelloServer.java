package cn.quyf.demo.netty.zhanbao.server;

import cn.quyf.demo.netty.zhanbao.server.handle.BaseServerHandle;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Netty中的所有handler都实现自ChannelHandler接口。按照输出输出来分，分为ChannelInboundHandler、ChannelOutboundHandler两大类。ChannelInboundHandler对从客户端发往服务器的报文进行处理，一般用来执行解码、读取客户端数据、进行业务处理等；ChannelOutboundHandler对从服务器发往客户端的报文进行处理，一般用来进行编码、发送报文到客户端。

	Netty中，可以注册多个handler。ChannelInboundHandler按照注册的先后顺序执行；ChannelOutboundHandler按照注册的先后顺序逆序执行
 * @author Administrator
 *
 */
public class HelloServer {

	public static void main(String[] args) {
		
		HelloServer server = new HelloServer();
		server.start( 8000 );
	}

	private void start(int port) {
		EventLoopGroup worker = new NioEventLoopGroup();
		EventLoopGroup boss  = new NioEventLoopGroup();
		
		ServerBootstrap s = new ServerBootstrap();
		s.group( boss,worker).channel( NioServerSocketChannel.class)
		.childOption(ChannelOption.TCP_NODELAY, true)
		.childOption(ChannelOption.SO_BACKLOG, 128)
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.childHandler( new ChannelInitializer<SocketChannel>() {

			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new StringDecoder());
				ch.pipeline().addLast(new BaseServerHandle());
			}
		});
		
		try {
			ChannelFuture f = s.bind( port ).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally{
			worker.shutdownGracefully();
			boss.shutdownGracefully();
		}
	}
}

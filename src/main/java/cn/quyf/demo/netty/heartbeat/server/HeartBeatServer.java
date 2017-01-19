package cn.quyf.demo.netty.heartbeat.server;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

/**
 * http://blog.csdn.net/linuu/article/details/51404264
 * @author quyf
 *
 */
public class HeartBeatServer {

	public void start(int port){
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap s = new ServerBootstrap();
		s.group(boss, worker);
		s.channel( NioServerSocketChannel.class)
		.childOption(ChannelOption.SO_KEEPALIVE, true)
		.option( ChannelOption.SO_BACKLOG, 128)
		.childHandler( new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pp = ch.pipeline();
				pp.addLast(new IdleStateHandler(5, 0, 0,TimeUnit.SECONDS));
				pp.addLast("decoder", new StringDecoder());
				pp.addLast("encoder",new StringEncoder());
				pp.addLast(new HeartBeatServerHandler());
			}
		});
		
		try {
			  // 绑定端口，开始接收进来的连接  
			ChannelFuture f = s.bind(port).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}
	
	public static void main(String[] args) {
		HeartBeatServer server = new HeartBeatServer();
		server.start( 8001 );
	}
	
}

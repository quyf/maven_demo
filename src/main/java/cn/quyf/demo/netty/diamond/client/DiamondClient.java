package cn.quyf.demo.netty.diamond.client;

import java.util.concurrent.TimeUnit;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class DiamondClient {
	String host;
	int port;
	private String msg;
	
	private EventLoopGroup group = new NioEventLoopGroup();
	private Bootstrap bootstrap;
	private volatile Channel channel;
	private volatile ChannelFuture future;
	
	private DiamondClientHandler handler;
	private int connectTimeout = 30000;
	
	public DiamondClient(String host, int port,String msg) throws Exception{
		this.host = host;
		this.port = port;
		this.msg = msg;
		handler = new DiamondClientHandler();
		doOpen();
		
		connect();
	}
	
	private void doOpen() {
		bootstrap = new Bootstrap();
		bootstrap.group(group)
		.channel(NioSocketChannel.class)
		.option(ChannelOption.SO_KEEPALIVE, true)
		.option(ChannelOption.TCP_NODELAY, true)
		.handler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ch.pipeline().addLast(new SendConnectInfoHandler( getMsg()));
				ch.pipeline().addLast(handler);
			}
		});
	}
	private void connect() throws InterruptedException {
		if( isConnect() ){
			return;
		}
		ChannelFuture f = bootstrap.connect(host, port).sync();
		try{
			if( future.isSuccess()){
				Channel newChannel = future.sync().channel();
				try{
					Channel oldChannel = DiamondClient.this.channel;
					if(oldChannel!=null){
						oldChannel.close();
					}
				}finally{
					DiamondClient.this.channel = newChannel;
				}
			}else if(future.cause()!=null){
				
			}else{
				
			}
		}finally{
            if (! isConnect()) {
                future.cancel(true);
            }
        }
	}
	private boolean isConnect(){
		if( channel==null){
			return false;
		}
		return channel.isActive();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String receiveMessage() {
		return handler.getMessage();
	}
	
	public String receiveMessage(long timeout) {
		return handler.getMessage(timeout);
	}
	
	 public static void main(String[] args) throws Exception {
		DiamondClient client = new DiamondClient("localhost", 8080, "getPwd");
		String message = client.receiveMessage();
		System.out.println(message);
	}
}

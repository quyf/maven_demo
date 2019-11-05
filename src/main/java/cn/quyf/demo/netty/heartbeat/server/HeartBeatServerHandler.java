package cn.quyf.demo.netty.heartbeat.server;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;

public class HeartBeatServerHandler extends ChannelHandlerAdapter {

	private int loss_connect_time= 0;
	
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		if( evt instanceof IdleStateEvent){
			IdleStateEvent event = (IdleStateEvent) evt;
			if( event.state() == IdleState.READER_IDLE ){
				loss_connect_time ++ ;
				System.out.println("5 s没有接收到客户端消息了");
				if( loss_connect_time>2 ){
					System.out.println("关闭这个不活跃的channel");
					ctx.channel().close();
				}
			}
		}else{
		}
	}
	
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		System.out.println("server channel read...."+Thread.currentThread().getName());
		System.out.println( ctx.channel().remoteAddress()+"->server:"+ msg.toString());
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}
	
}

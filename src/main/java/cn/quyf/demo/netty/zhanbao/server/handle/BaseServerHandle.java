package cn.quyf.demo.netty.zhanbao.server.handle;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class BaseServerHandle extends ChannelHandlerAdapter {
	private int counter;  
	
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String body = (String)msg;
		System.out.println("server receive order : " + body + ";the counter is: " + ++counter);  
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		ctx.close();
	}
}

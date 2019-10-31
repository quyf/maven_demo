package cn.quyf.demo.netty.mutilprotocol.client;

import cn.quyf.demo.netty.mutilprotocol.Person;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {

	private Person person;
	public ClientHandler(Person person) {
		this.person = person;
	}
	
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.write(person);
		ctx.flush();
		System.out.println("ClientHandler--channelActive->"+person);
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}

package cn.quyf.demo.netty.protocol.client;

import cn.quyf.demo.netty.protocol.Person;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		Person person = new Person();
		person.setName("guowl");
		person.setSex("man");
		person.setAge(30);
		ctx.write(person);
		ctx.flush();
	}
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
}

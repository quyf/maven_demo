package cn.quyf.demo.netty.diamond.server;

import java.net.SocketAddress;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;

public class DiamondServerHandler extends ChannelHandlerAdapter{
	private static final Logger log = LoggerFactory.getLogger(DiamondServerHandler.class);
    private final Charset charset = Charset.forName("UTF-8");
    
	public static Map<String, ChannelHandlerContext> channels = new HashMap<String, ChannelHandlerContext>();
	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("channel:"+ctx.channel().remoteAddress()+" 连接到服务器（channelRead）");
	}
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String request = (String)msg;
		System.out.println("channelread==" + request);
		
		String response = "";
		if( request.equals("getPwd")){
			response = "pwd:a123456";
		}
		
		String clientIp = ctx.channel().remoteAddress().toString();
		channels.put(clientIp, ctx);
		
		sendMsg(response, ctx);
	}
	
	private void sendMsg(String response, ChannelHandlerContext ctx){
		byte[] resByte = response.getBytes();
		ByteBuf buf = Unpooled.buffer();
		buf.writeBytes(resByte);
		ctx.writeAndFlush(buf);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
		log.info("channelReadComplete。。");
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		String ip = ctx.channel().remoteAddress().toString();
		log.info("ip="+ip+";;channelRegistered");
	}


	@Override
	public void channelInactive(ChannelHandlerContext ctx) throws Exception {
		String ip = ctx.channel().remoteAddress().toString();
		log.info("ip="+ip+";;channelInactive");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		String ip = ctx.channel().remoteAddress().toString();
		log.info("ip="+ip+";;userEventTriggered");
	}

	@Override
	public void connect(ChannelHandlerContext ctx, SocketAddress remoteAddress, SocketAddress localAddress,
			ChannelPromise promise) throws Exception {
		String ip = ctx.channel().remoteAddress().toString();
		
		log.info("ip="+ip+";;connect,,remote="+remoteAddress.toString());
	}

	@Override
	public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
		super.close(ctx, promise);
		
	}

}

package cn.quyf.demo.netty.diamond.client;

import java.nio.charset.Charset;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端连接到server，发送client信息：superdiamond,projCode,profile\r\n
 * 
 * Create on @2013-8-24 @下午10:31:29 
 * @author bsli@ustcinfo.com
 */
public class SendConnectInfoHandler extends ChannelHandlerAdapter {
	
	private String clientMsg;
	
	private final Charset charset;
    
    public SendConnectInfoHandler(String clientMsg) {
    	charset = Charset.forName("UTF-8");
		this.clientMsg = clientMsg;
	}
    
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		System.out.println("client channelActive");
		String msg = clientMsg + "\r\n";
		ByteBuf encoded = Unpooled.copiedBuffer(msg, charset);
        ctx.channel().writeAndFlush(encoded);
	}
}

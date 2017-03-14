package cn.quyf.demo.netty.zhanbao.client.handle;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class BaseClientHandler extends ChannelHandlerAdapter {

	 private byte[] req;  
     
	 private int counter;  
	    
	 public BaseClientHandler(){
		 req = ("In this chapter you general, we recommend Java Concurrency in Practice by Brian Goetz. His book w"  
	                + "ill give We’ve reached an exciting point—in the next chapter we’ll discuss bootstrapping, the process "  
	                + "of configuring and connecting all of Netty’s components to bring your learned about threading models in ge"  
	                + "neral and Netty’s threading model in particular, whose performance and consistency advantages we discuss"  
	                + "ed in detail In this chapter you general, we recommend Java Concurrency in Practice by Brian Goetz. Hi"  
	                + "s book will give We’ve reached an exciting point—in the next chapter we’ll discuss bootstrapping, the"  
	                + " process of configuring and connecting all of Netty’s components to bring your learned about threading "  
	                + "models in general and Netty’s threading model in particular, whose performance and consistency advantag"  
	                + "es we discussed in detailIn this chapter you general, we recommend Java Concurrency in Practice by Bri"  
	                + "an Goetz. His book will give We’ve reached an exciting point—in the next chapter;the counter is: 1 2222"  
	                + "sdsa ddasd asdsadas dsadasdas").getBytes();  
	 } 
	 
	//当连接建立的时候向服务端发送消息 ，channelActive 事件当连接建立的时候会触发
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ByteBuf buf = Unpooled.buffer( req.length );
		buf.writeBytes( req );
		ctx.writeAndFlush( buf );
		
		buf = Unpooled.buffer( req.length );
		buf.writeBytes( req );
		ctx.writeAndFlush(buf);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		String buf = (String) msg;  
        System.out.println("Now is : " + buf + " ; the counter is : "+ ++counter);  
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		 ctx.close();  
	}
}

package cn.quyf.demo.netty.diamond.client;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class DiamondClientHandler extends ChannelHandlerAdapter {

	private final LinkedBlockingQueue<String> queue;
	 
	public DiamondClientHandler() {
		queue = new LinkedBlockingQueue<String>();
	}
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		queue.add((String)msg);
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		super.channelReadComplete(ctx);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, cause);
	}
	
	public String getMessage() {
		String message = null;
		try {
			message = queue.take();
		} catch (InterruptedException e) {
		}
		return message;
	}
    
    /**
     * 
     * @param timeout 超时时间，单位秒
     * @return
     */
    public String getMessage(long timeout) {
		String message = null;
		try {
			message = queue.poll(timeout, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
		}
		return message;
	}
}

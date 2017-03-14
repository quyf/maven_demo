package cn.quyf.demo.netty.mutilprotocol.server;

import java.util.List;

import cn.quyf.demo.netty.protocol.ByteBufToBytes;
import cn.quyf.demo.netty.protocol.Person;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class StringDecoder extends ByteToMessageDecoder {

	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("Server StringDecoder--decode");
		byte n = "n".getBytes()[0];
		byte p = in.readByte();
		in.resetReaderIndex();
		if( n==p ){
			ByteBufToBytes read = new ByteBufToBytes();
			String msg = new String( read.read(in));
			Person person = buildPerson( msg );
			out.add(person);
		}else{
			ctx.fireChannelRead( in );
		}
	}

	private Person buildPerson(String msg) {
		Person p = new Person();
		String[] arr = msg.split(";|:");
		p.setName(arr[1]);
		p.setAge(Integer.parseInt(arr[3]));
		p.setSex(arr[5]);
		return p;
	}

}

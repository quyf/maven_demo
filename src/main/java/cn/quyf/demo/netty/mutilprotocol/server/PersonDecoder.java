package cn.quyf.demo.netty.mutilprotocol.server;

import java.util.List;

import cn.quyf.demo.netty.protocol.ByteBufToBytes;
import cn.quyf.demo.netty.protocol.ByteObjConverter;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * PersonDecoder：把ByteBuf流转换成Person对象，其中ByteBufToBytes是读取ButeBuf的工具类，上一篇文章中提到过，在此不在详述。
 * ByteObjConverter是byte和obj的互相转换的工具。
 * @author quyf
 *
 *Person通讯协议：二进制流与Person对象间的互相转换。
 *
 */
public class PersonDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		System.out.println("Server PersonDecoder--decode");
		byte n = "n".getBytes()[0];//string协议以n开头的，
		byte p = in.readByte();
		// 把读取的起始位置重置
		in.resetReaderIndex();
		if( n!=p ){//如果不=，则不是string协议，是persondecoder协议
			ByteBufToBytes read = new ByteBufToBytes();
			Object obj = ByteObjConverter.ByteToObject(read.read(in));
			out.add(obj);
		}else{
			//执行其他的decode
			ctx.fireChannelRead( in );
		}
	}

	
}

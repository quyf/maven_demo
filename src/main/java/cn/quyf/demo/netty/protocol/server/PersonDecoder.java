package cn.quyf.demo.netty.protocol.server;

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
 */
public class PersonDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		ByteBufToBytes read = new ByteBufToBytes();
		Object obj = ByteObjConverter.ByteToObject(read.read(in));
		out.add(obj);
	}

	
}

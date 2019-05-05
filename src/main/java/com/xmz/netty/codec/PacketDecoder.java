package com.xmz.netty.codec;

import com.xmz.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.codec
 * @class: PacketDecoder.java
 * @description:
 * @Date 2019-04-30 15:38
 */
public class PacketDecoder extends ByteToMessageDecoder {

		@Override
		protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
				out.add(PacketCodeC.INSTANCE.decode(in));
		}
}

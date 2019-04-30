package com.xmz.netty.codec;

import com.xmz.netty.protocol.Packet;
import com.xmz.netty.protocol.PacketCodeC;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.codec
 * @class: PacketEncoder.java
 * @description:
 * @Date 2019-04-30 15:35
 */
public class PacketEncoder extends MessageToByteEncoder<Packet> {

		@Override
		protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
				PacketCodeC.instance.encode(out, msg);
		}
}

package com.xmz.netty.base;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.base
 * @class: FirstServerHandler.java
 * @description:
 * @Date 2019-04-30 16:11
 */
public class FirstServerHandler extends ChannelInboundHandlerAdapter {
		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) {
				ByteBuf byteBuf = (ByteBuf) msg;

				System.out.println(new Date() + ": 服务端读到数据 -> " + byteBuf.toString(Charset.forName("utf-8")));

				// 回复数据到客户端
				System.out.println(new Date() + ": 服务端写出数据");
				ByteBuf out = getByteBuf(ctx);
				ctx.channel().writeAndFlush(out);
		}


		private ByteBuf getByteBuf(ChannelHandlerContext ctx) {
				byte[] bytes = "你好，大兄弟.吃饭了么".getBytes(Charset.forName("utf-8"));

				ByteBuf buffer = ctx.alloc().buffer();

				buffer.writeBytes(bytes);

				return buffer;
		}

}

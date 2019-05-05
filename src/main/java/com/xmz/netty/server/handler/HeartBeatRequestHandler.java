package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.HeartBeatRequestPacket;
import com.xmz.netty.protocol.response.HeartBeatResponsePacket;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: HeartBeatRequestHandler.java
 * @description:
 * @Date 2019-05-05 16:02
 */
@ChannelHandler.Sharable
public class HeartBeatRequestHandler extends SimpleChannelInboundHandler<HeartBeatRequestPacket> {
		public static final HeartBeatRequestHandler INSTANCE = new HeartBeatRequestHandler();

		private HeartBeatRequestHandler() {

		}

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, HeartBeatRequestPacket requestPacket) {
				ctx.writeAndFlush(new HeartBeatResponsePacket());
		}
}

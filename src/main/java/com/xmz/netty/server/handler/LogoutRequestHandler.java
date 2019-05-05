package com.xmz.netty.server.handler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: LogoutRequestHandler.java
 * @description:
 * @Date 2019-05-05 13:40
 */

import com.xmz.netty.protocol.request.LogoutRequestPacket;
import com.xmz.netty.protocol.response.LogoutResponsePacket;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

public class LogoutRequestHandler extends SimpleChannelInboundHandler<LogoutRequestPacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, LogoutRequestPacket msg) {
				SessionUtil.unBindSession(ctx.channel());
				LogoutResponsePacket logoutResponsePacket = new LogoutResponsePacket();
				logoutResponsePacket.setSuccess(true);
				ctx.channel().writeAndFlush(logoutResponsePacket);
		}
}

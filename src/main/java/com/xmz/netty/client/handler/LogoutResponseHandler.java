package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.LogoutResponsePacket;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.handler
 * @class: LogoutResponseHandler.java
 * @description:
 * @Date 2019-05-05 13:18
 */
public class LogoutResponseHandler extends SimpleChannelInboundHandler<LogoutResponsePacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, LogoutResponsePacket logoutResponsePacket) {
				SessionUtil.unBindSession(ctx.channel());
		}
}

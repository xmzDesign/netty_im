package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.JoinGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.handler
 * @class: JoinGroupResponseHandler.java
 * @description:
 * @Date 2019-05-05 14:16
 */
public class JoinGroupResponseHandler extends SimpleChannelInboundHandler<JoinGroupResponsePacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, JoinGroupResponsePacket responsePacket) {
				if (responsePacket.isSuccess()) {
						System.out.println("加入群[" + responsePacket.getGroupId() + "]成功!");
				} else {
						System.err.println("加入群[" + responsePacket.getGroupId() + "]失败，原因为：" + responsePacket.getReason());
				}
		}
}

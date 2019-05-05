package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.QuitGroupRequestPacket;
import com.xmz.netty.protocol.response.QuitGroupResponsePacket;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: QuitGroupRequestHandler.java
 * @description:
 * @Date 2019-05-05 14:16
 */
public class QuitGroupRequestHandler extends SimpleChannelInboundHandler<QuitGroupRequestPacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, QuitGroupRequestPacket requestPacket) {
				// 1. 获取群对应的 channelGroup，然后将当前用户的 channel 移除
				String groupId = requestPacket.getGroupId();
				ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
				channelGroup.remove(ctx.channel());

				// 2. 构造退群响应发送给客户端
				QuitGroupResponsePacket responsePacket = new QuitGroupResponsePacket();

				responsePacket.setGroupId(requestPacket.getGroupId());
				responsePacket.setSuccess(true);
				ctx.channel().writeAndFlush(responsePacket);

		}
}

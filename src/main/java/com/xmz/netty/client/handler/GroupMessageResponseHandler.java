package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.GroupMessageResponsePacket;
import com.xmz.netty.session.Session;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.handler
 * @class: GroupMessageResponseHandler.java
 * @description:
 * @Date 2019-05-05 14:47
 */
public class GroupMessageResponseHandler extends SimpleChannelInboundHandler<GroupMessageResponsePacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, GroupMessageResponsePacket responsePacket) {
				String fromGroupId = responsePacket.getFromGroupId();
				Session fromUser = responsePacket.getFromUser();
				System.out.println("收到群[" + fromGroupId + "]中[" + fromUser + "]发来的消息：" + responsePacket.getMessage());
		}
}

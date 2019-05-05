package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.GroupMessageRequestPacket;
import com.xmz.netty.protocol.response.GroupMessageResponsePacket;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: GroupMessageRequestHandler.java
 * @description:
 * @Date 2019-05-05 14:41
 */
public class GroupMessageRequestHandler extends SimpleChannelInboundHandler<GroupMessageRequestPacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, GroupMessageRequestPacket requestPacket) {
				// 1.拿到 groupId 构造群聊消息的响应
				String groupId = requestPacket.getToGroupId();
				GroupMessageResponsePacket responsePacket = new GroupMessageResponsePacket();
				responsePacket.setFromGroupId(groupId);
				responsePacket.setMessage(requestPacket.getMessage());
				responsePacket.setFromUser(SessionUtil.getSession(ctx.channel()));


				// 2. 拿到群聊对应的 channelGroup，写到每个客户端
				ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);
				channelGroup.writeAndFlush(responsePacket);
		}
}

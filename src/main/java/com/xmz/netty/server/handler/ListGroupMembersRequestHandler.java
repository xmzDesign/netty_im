package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.ListGroupMembersRequestPacket;
import com.xmz.netty.protocol.response.ListGroupMembersResponsePacket;
import com.xmz.netty.session.Session;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: ListGroupMembersRequestHandler.java
 * @description:
 * @Date 2019-05-05 14:17
 */
public class ListGroupMembersRequestHandler extends SimpleChannelInboundHandler<ListGroupMembersRequestPacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, ListGroupMembersRequestPacket requestPacket) throws Exception {
				// 1. 获取群的 ChannelGroup
				String groupId = requestPacket.getGroupId();
				ChannelGroup channelGroup = SessionUtil.getChannelGroup(groupId);

				// 2. 遍历群成员的 channel，对应的 session，构造群成员的信息
				List<Session> sessionList = new ArrayList<>();
				for (Channel channel : channelGroup) {
						Session session = SessionUtil.getSession(channel);
						sessionList.add(session);
				}

				// 3. 构建获取成员列表响应写回到客户端
				ListGroupMembersResponsePacket responsePacket = new ListGroupMembersResponsePacket();

				responsePacket.setGroupId(groupId);
				responsePacket.setSessionList(sessionList);
				ctx.channel().writeAndFlush(responsePacket);
		}
}

package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.CreateGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.handler
 * @class: CreateGroupResponseHandler.java
 * @description:
 * @Date 2019-05-05 13:15
 */
public class CreateGroupResponseHandler extends SimpleChannelInboundHandler<CreateGroupResponsePacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, CreateGroupResponsePacket createGroupResponsePacket) {
				System.out.print("群创建成功，id 为[" + createGroupResponsePacket.getGroupId() + "], ");
				System.out.println("群里面有：" + createGroupResponsePacket.getUserNameList());
		}
}

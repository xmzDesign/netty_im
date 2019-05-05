package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.QuitGroupResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.handler
 * @class: QuitGroupResponseHandler.java
 * @description:
 * @Date 2019-05-05 14:21
 */
public class QuitGroupResponseHandler extends SimpleChannelInboundHandler<QuitGroupResponsePacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, QuitGroupResponsePacket responsePacket) {
				if (responsePacket.isSuccess()) {
						System.out.println("退出群聊[" + responsePacket.getGroupId() + "]成功！");
				} else {
						System.out.println("退出群聊[" + responsePacket.getGroupId() + "]失败！");
				}

		}
}

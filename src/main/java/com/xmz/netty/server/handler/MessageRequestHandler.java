package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
import com.xmz.netty.session.Session;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server
 * @class: MessageRequestHandler.java
 * @description:
 * @Date 2019-04-30 15:25
 */
public class MessageRequestHandler extends SimpleChannelInboundHandler<MessageRequestPacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, MessageRequestPacket messageRequestPacket) throws Exception {

				// 1.拿到消息发送方的会话信息
				Session session = SessionUtil.getSession(ctx.channel());

				// 2.通过消息发送方的会话信息构造要发送的消息
				MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
				messageResponsePacket.setFromUserId(session.getUserId());
				messageResponsePacket.setFromUserName(session.getUserName());
				messageResponsePacket.setMessage(messageRequestPacket.getMessage());

				// 3.拿到消息接收方的 channel
				Channel toUserChannel = SessionUtil.getChannel(messageRequestPacket.getToUserId());

				// 4.将消息发送给消息接收方
				if (toUserChannel != null && SessionUtil.hasLogin(toUserChannel)) {
						toUserChannel.writeAndFlush(messageResponsePacket);
				} else {
						System.err.println("[" + messageRequestPacket.getToUserId() + "] 不在线，发送失败!");
				}
		}
}

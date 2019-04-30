package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
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
				MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
				System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
				messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");

				ctx.channel().writeAndFlush(messageResponsePacket);
		}
}

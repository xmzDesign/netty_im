package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.response.MessageResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client
 * @class: MessageResponseHandler.java
 * @description:
 * @Date 2019-04-30 15:46
 */
public class MessageResponseHandler extends SimpleChannelInboundHandler<MessageResponsePacket> {
		@Override
		protected void channelRead0(ChannelHandlerContext ctx, MessageResponsePacket messageResponsePacket) {
				System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
		}


}

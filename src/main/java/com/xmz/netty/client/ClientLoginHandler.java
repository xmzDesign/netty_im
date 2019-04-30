package com.xmz.netty.client;

import com.xmz.netty.protocol.Packet;
import com.xmz.netty.protocol.PacketCodeC;
import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
import com.xmz.netty.util.LoginUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client
 * @class: ClientLoginHandler.java
 * @description:
 * @Date 2019-04-30 11:33
 */
public class ClientLoginHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelActive(ChannelHandlerContext ctx) throws Exception {
				LoginRequestPacket loginRequestPacket=new LoginRequestPacket();
				loginRequestPacket.setUserId("001");
				loginRequestPacket.setUsername("xmz");
				loginRequestPacket.setPassword("xmz123");
				loginRequestPacket.setVersion((byte) 1);
				//编码准备传输
				ByteBuf encode = PacketCodeC.instance.encode(ctx.alloc(), loginRequestPacket);
				ctx.channel().writeAndFlush(encode);
		}

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
				ByteBuf byteBuf= (ByteBuf) msg;
				Packet decode = PacketCodeC.instance.decode(byteBuf);
				if (decode instanceof LoginResponsePacket){
						LoginResponsePacket responsePacket= (LoginResponsePacket) decode;
						if (responsePacket.isSuccess()){
								LoginUtil.markAsLogin(ctx.channel());
								System.out.println("客户端登录成功");
						}else {
								System.out.println("客户端登录失败，原因"+responsePacket.getReason());
						}
				}else if (decode instanceof MessageResponsePacket){
						MessageResponsePacket messageResponsePacket = (MessageResponsePacket) decode;
						System.out.println(new Date() + ": 收到服务端的消息: " + messageResponsePacket.getMessage());
				}
		}
}

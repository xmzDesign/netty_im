package com.xmz.netty.server;

import com.xmz.netty.protocol.Packet;
import com.xmz.netty.protocol.PacketCodeC;
import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server
 * @class: ServerLoginHandler.java
 * @description: 登录处理器
 * @Date 2019-04-30 10:30
 */
public class ServerLoginHandler extends ChannelInboundHandlerAdapter {



		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
				System.out.println(new Date() + ": 客户端开始登录……");
				ByteBuf byteBuf= (ByteBuf) msg;
				Packet packet = PacketCodeC.instance.decode(byteBuf);
				if (packet instanceof LoginRequestPacket){
						LoginRequestPacket loginRequestPacket= (LoginRequestPacket) packet;
						LoginResponsePacket loginResponsePacket=new LoginResponsePacket();
						loginResponsePacket.setVersion(loginRequestPacket.getVersion());
						if (valid(loginRequestPacket)) {
								loginResponsePacket.setSuccess(true);
								System.out.println(new Date() + ": 登录成功!");
						} else {
								loginResponsePacket.setReason("账号密码校验失败");
								loginResponsePacket.setSuccess(false);
								System.out.println(new Date() + ": 登录失败!");
						}
						ByteBuf encode = PacketCodeC.instance.encode(ctx.alloc(), loginResponsePacket);
						ctx.channel().writeAndFlush(encode);

				}else if (packet instanceof MessageRequestPacket) {
						// 客户端发来消息
						MessageRequestPacket messageRequestPacket = ((MessageRequestPacket) packet);

						MessageResponsePacket messageResponsePacket = new MessageResponsePacket();
						System.out.println(new Date() + ": 收到客户端消息: " + messageRequestPacket.getMessage());
						messageResponsePacket.setMessage("服务端回复【" + messageRequestPacket.getMessage() + "】");
						ByteBuf responseByteBuf = PacketCodeC.instance.encode(ctx.alloc(), messageResponsePacket);
						ctx.channel().writeAndFlush(responseByteBuf);
				}
		}

		private boolean valid(LoginRequestPacket loginRequestPacket) {
				return true;
		}
}

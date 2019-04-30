package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.util.LoginUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import java.util.UUID;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client
 * @class: LoginResponseHandler.java
 * @description:
 * @Date 2019-04-30 15:45
 */
public class LoginResponseHandler extends SimpleChannelInboundHandler<LoginResponsePacket> {
		@Override
		public void channelActive(ChannelHandlerContext ctx) {
				// 创建登录对象
				LoginRequestPacket loginRequestPacket = new LoginRequestPacket();
				loginRequestPacket.setUserId(UUID.randomUUID().toString());
				loginRequestPacket.setUsername("flash");
				loginRequestPacket.setPassword("pwd");
				System.out.println("登录数据传递："+loginRequestPacket.toString());
				// 写数据
				ctx.channel().writeAndFlush(loginRequestPacket);
		}

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
				if (loginResponsePacket.isSuccess()) {
						System.out.println(new Date() + ": 客户端登录成功");
						LoginUtil.markAsLogin(ctx.channel());
				} else {
						System.out.println(new Date() + ": 客户端登录失败，原因：" + loginResponsePacket.getReason());
				}
		}
}

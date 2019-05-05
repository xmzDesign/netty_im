package com.xmz.netty.client.handler;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.session.Session;
import com.xmz.netty.util.LoginUtil;
import com.xmz.netty.util.SessionUtil;
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
		protected void channelRead0(ChannelHandlerContext ctx, LoginResponsePacket loginResponsePacket) {
				String userId = loginResponsePacket.getUserId();
				String userName = loginResponsePacket.getUserName();

				if (loginResponsePacket.isSuccess()) {
						System.out.println("[" + userName + "]登录成功，userId 为: " + loginResponsePacket.getUserId());
						SessionUtil.bindSession(new Session(userId, userName), ctx.channel());
				} else {
						System.out.println("[" + userName + "]登录失败，原因：" + loginResponsePacket.getReason());
				}
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) {
				System.out.println("客户端连接被关闭!");
		}
}

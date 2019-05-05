package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.session.Session;
import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;
import java.util.UUID;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server
 * @class: LoginRequestHandler.java
 * @description:
 * @Date 2019-04-30 15:21
 */
public class LoginRequestHandler extends SimpleChannelInboundHandler<LoginRequestPacket> {

		@Override
		protected void channelRead0(ChannelHandlerContext ctx, LoginRequestPacket msg) throws Exception {
				LoginResponsePacket loginResponsePacket = login(ctx,msg);
				ctx.channel().writeAndFlush(loginResponsePacket);
		}

		private LoginResponsePacket login(ChannelHandlerContext ctx,LoginRequestPacket loginRequestPacket) {
				LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
				loginResponsePacket.setVersion(loginRequestPacket.getVersion());
				if (valid(loginRequestPacket)) {
						loginResponsePacket.setSuccess(true);
						String userId = randomUserId();
						loginResponsePacket.setUserId(userId);
						loginResponsePacket.setUserName(loginRequestPacket.getUsername());

						System.out.println("[" + loginRequestPacket.getUsername() + "]登录成功");
						SessionUtil.bindSession(new Session(userId, loginRequestPacket.getUsername()), ctx.channel());
				} else {
						loginResponsePacket.setReason("账号密码校验失败");
						loginResponsePacket.setSuccess(false);
						System.out.println(new Date() + ": 登录失败!");
				}
				return loginResponsePacket;
		}

		@Override
		public void channelInactive(ChannelHandlerContext ctx) {
				SessionUtil.unBindSession(ctx.channel());
		}

		private static String randomUserId() {
				return UUID.randomUUID().toString().split("-")[0];
		}

		private boolean valid(LoginRequestPacket loginRequestPacket) {
				return true;
		}
}

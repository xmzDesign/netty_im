package com.xmz.netty.server.handler;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Date;

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
				LoginResponsePacket loginResponsePacket = login(msg);
				ctx.channel().writeAndFlush(loginResponsePacket);
		}

		private LoginResponsePacket login(LoginRequestPacket loginRequestPacket) {
				LoginResponsePacket loginResponsePacket = new LoginResponsePacket();
				loginResponsePacket.setVersion(loginRequestPacket.getVersion());
				if (valid(loginRequestPacket)) {
						loginResponsePacket.setSuccess(true);
						System.out.println(new Date() + ": 登录成功!");
				} else {
						loginResponsePacket.setReason("账号密码校验失败");
						loginResponsePacket.setSuccess(false);
						System.out.println(new Date() + ": 登录失败!");
				}
				return loginResponsePacket;
		}

		private boolean valid(LoginRequestPacket loginRequestPacket) {
				return true;
		}
}

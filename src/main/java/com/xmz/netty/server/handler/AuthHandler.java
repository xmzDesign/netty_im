package com.xmz.netty.server.handler;


import com.xmz.netty.util.SessionUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server.handler
 * @class: AuthHandler.java
 * @description: 认证处理器
 * @Date 2019-05-05 09:36
 */
public class AuthHandler extends ChannelInboundHandlerAdapter {

		@Override
		public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
				if (!SessionUtil.hasLogin(ctx.channel())) {
						ctx.channel().close();
				} else {
						ctx.pipeline().remove(this);
						super.channelRead(ctx, msg);
				}
		}

}

package com.xmz.netty.server;

import com.xmz.netty.codec.PacketDecoder;
import com.xmz.netty.codec.PacketEncoder;
import com.xmz.netty.server.handler.AuthHandler;
import com.xmz.netty.server.handler.CreateGroupRequestHandler;
import com.xmz.netty.server.handler.GroupMessageRequestHandler;
import com.xmz.netty.server.handler.JoinGroupRequestHandler;
import com.xmz.netty.server.handler.ListGroupMembersRequestHandler;
import com.xmz.netty.server.handler.LoginRequestHandler;
import com.xmz.netty.server.handler.LogoutRequestHandler;
import com.xmz.netty.server.handler.MessageRequestHandler;
import com.xmz.netty.server.handler.QuitGroupRequestHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.server
 * @class: NettyServer.java
 * @description:
 * @Date 2019-04-30 10:17
 */
public class NettyServer {
		private static final int PORT = 8000;

		public static void main(String[] args) {
				NioEventLoopGroup boosGroup = new NioEventLoopGroup();
				NioEventLoopGroup workerGroup = new NioEventLoopGroup();

				final ServerBootstrap serverBootstrap = new ServerBootstrap();
				serverBootstrap
						.group(boosGroup, workerGroup)
						.channel(NioServerSocketChannel.class)
						.option(ChannelOption.SO_BACKLOG, 1024)
						.childOption(ChannelOption.SO_KEEPALIVE, true)
						.childOption(ChannelOption.TCP_NODELAY, true)
						.childHandler(new ChannelInitializer<NioSocketChannel>() {
								@Override
								protected void initChannel(NioSocketChannel ch) {
										ch.pipeline().addLast(new Spliter());
										ch.pipeline().addLast(new PacketDecoder());
										// 登录请求处理器
										ch.pipeline().addLast(new LoginRequestHandler());
										ch.pipeline().addLast(new AuthHandler());
										// 单聊消息请求处理器
										ch.pipeline().addLast(new MessageRequestHandler());
										// 创建群请求处理器
										ch.pipeline().addLast(new CreateGroupRequestHandler());
										// 加群请求处理器
										ch.pipeline().addLast(new JoinGroupRequestHandler());
										// 退群请求处理器
										ch.pipeline().addLast(new QuitGroupRequestHandler());
										// 获取群成员请求处理器
										ch.pipeline().addLast(new ListGroupMembersRequestHandler());
										//群消息
										ch.pipeline().addLast(new GroupMessageRequestHandler());
										// 登出请求处理器
										ch.pipeline().addLast(new LogoutRequestHandler());
										ch.pipeline().addLast(new PacketEncoder());
								}
						});


				bind(serverBootstrap, PORT);

		}


		private static void bind(final ServerBootstrap serverBootstrap, final int port) {
				serverBootstrap.bind(port).addListener(future -> {
						if (future.isSuccess()) {
								System.out.println(new Date() + ": 端口[" + port + "]绑定成功!");
						} else {
								System.err.println("端口[" + port + "]绑定失败!");
						}
				});
		}

}

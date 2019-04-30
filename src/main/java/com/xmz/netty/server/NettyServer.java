package com.xmz.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
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
//										ch.pipeline().addLast(new ServerLoginHandler());
										ch.pipeline().addLast(new InBoundHandlerA());
										ch.pipeline().addLast(new InBoundHandlerB());
										ch.pipeline().addLast(new InBoundHandlerC());
								}
						});


				bind(serverBootstrap, PORT);

		}


		public static class InBoundHandlerA extends ChannelInboundHandlerAdapter {
				@Override
				public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
						System.out.println("InBoundHandlerA: " + msg);
						super.channelRead(ctx, msg);
				}
		}

		public static class InBoundHandlerB extends ChannelInboundHandlerAdapter {
				@Override
				public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
						System.out.println("InBoundHandlerB: " + msg);
						super.channelRead(ctx, msg);
				}
		}

		public static class InBoundHandlerC extends ChannelInboundHandlerAdapter {
				@Override
				public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
						System.out.println("InBoundHandlerC: " + msg);
						super.channelRead(ctx, msg);
				}
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

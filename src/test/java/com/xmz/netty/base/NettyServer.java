package com.xmz.netty.base;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.base
 * @class: NettyServer.java
 * @description:
 * @Date 2019-04-29 15:38
 */
public class NettyServer {
		public static void main(String[] args) {
				NioEventLoopGroup bossGroup = new NioEventLoopGroup();
				NioEventLoopGroup workerGroup = new NioEventLoopGroup();

				ServerBootstrap serverBootstrap = new ServerBootstrap();
				serverBootstrap
						.group(bossGroup, workerGroup)
						.channel(NioServerSocketChannel.class)
						//childHandler()用于指定处理新连接数据的读写处理逻辑
						.childHandler(new ChannelInitializer<NioSocketChannel>() {
								protected void initChannel(NioSocketChannel ch) {
										ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
										ch.pipeline().addLast(new FirstServerHandler());
								}
						});
						//handler()用于指定在服务端启动过程中的一些逻辑
//						.handler(new ChannelInitializer<NioServerSocketChannel>() {
//						@Override
//						protected void initChannel(NioServerSocketChannel ch) throws Exception {
//
//						}
//				});

				serverBootstrap.bind(8000);
		}

}

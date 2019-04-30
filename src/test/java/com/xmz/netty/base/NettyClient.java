package com.xmz.netty.base;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.base
 * @class: NettyClient.java
 * @description:
 * @Date 2019-04-29 15:50
 */
public class NettyClient {
		public static void main(String[] args) {
				NioEventLoopGroup workerGroup = new NioEventLoopGroup();

				Bootstrap bootstrap = new Bootstrap();
				bootstrap
						// 1.指定线程模型
						.group(workerGroup)
						// 2.指定 IO 类型为 NIO
						.channel(NioSocketChannel.class)
						// 3.IO 处理逻辑
						.handler(new ChannelInitializer<SocketChannel>() {
								@Override
								public void initChannel(SocketChannel ch) {
										ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4));
										ch.pipeline().addLast(new FirstClientHandler());
								}
						});
				// 4.建立连接
				bootstrap.connect("127.0.0.1", 8000).addListener(future -> {
						if (future.isSuccess()) {
								System.out.println("连接成功!");
						} else {
								System.err.println("连接失败!");
						}

				});
		}
}

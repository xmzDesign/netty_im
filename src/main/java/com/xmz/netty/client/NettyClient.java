package com.xmz.netty.client;

import com.xmz.netty.client.console.ConsoleCommandManager;
import com.xmz.netty.client.console.LoginConsoleCommand;
import com.xmz.netty.client.handler.CreateGroupResponseHandler;
import com.xmz.netty.client.handler.GroupMessageResponseHandler;
import com.xmz.netty.client.handler.JoinGroupResponseHandler;
import com.xmz.netty.client.handler.ListGroupMembersResponseHandler;
import com.xmz.netty.client.handler.LoginResponseHandler;
import com.xmz.netty.client.handler.LogoutResponseHandler;
import com.xmz.netty.client.handler.MessageResponseHandler;
import com.xmz.netty.client.handler.QuitGroupResponseHandler;
import com.xmz.netty.codec.PacketDecoder;
import com.xmz.netty.codec.PacketEncoder;
import com.xmz.netty.server.Spliter;
import com.xmz.netty.util.SessionUtil;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client
 * @class: NettyClient.java
 * @description: 客户端
 * @Date 2019-04-30 11:41
 */
public class NettyClient {

		private static final int MAX_RETRY = 5;
		private static final String HOST = "127.0.0.1";
		private static final int PORT = 8000;

		public static void main(String[] args) {
				NioEventLoopGroup workerGroup = new NioEventLoopGroup();
				Bootstrap bootstrap=new Bootstrap();
				bootstrap.group(workerGroup).channel(NioSocketChannel.class)
						.option(ChannelOption.CONNECT_TIMEOUT_MILLIS,5000)
						.option(ChannelOption.SO_KEEPALIVE,true)
						.option(ChannelOption.TCP_NODELAY,Boolean.TRUE)
						.handler(new ChannelInitializer<SocketChannel>() {
								@Override
								protected void initChannel(SocketChannel ch) throws Exception {
										ch.pipeline().addLast(new Spliter());
										ch.pipeline().addLast(new PacketDecoder());
										// 登录响应处理器
										ch.pipeline().addLast(new LoginResponseHandler());
										// 收消息处理器
										ch.pipeline().addLast(new MessageResponseHandler());
										// 创建群响应处理器
										ch.pipeline().addLast(new CreateGroupResponseHandler());
										// 加群响应处理器
										ch.pipeline().addLast(new JoinGroupResponseHandler());
										// 退群响应处理器
										ch.pipeline().addLast(new QuitGroupResponseHandler());
										// 获取群成员响应处理器
										ch.pipeline().addLast(new ListGroupMembersResponseHandler());
										// 群消息
										ch.pipeline().addLast(new GroupMessageResponseHandler());
										// 登出响应处理器
										ch.pipeline().addLast(new LogoutResponseHandler());
										ch.pipeline().addLast(new PacketEncoder());
								}
						});
				connect(bootstrap, HOST, PORT, MAX_RETRY);
		}

		private static void connect(Bootstrap bootstrap, String host, int port, int retry) {
				bootstrap.connect(host, port).addListener(future -> {
						if (future.isSuccess()) {
								System.out.println(new Date() + ": 连接成功!");
								Channel channel = ((ChannelFuture) future).channel();
								startConsoleThread(channel);
						} else if (retry == 0) {
								System.err.println("重试次数已用完，放弃连接！");
						} else {
								// 第几次重连
								int order = (MAX_RETRY - retry) + 1;
								// 本次重连的间隔
								int delay = 1 << order;
								System.err.println(new Date() + ": 连接失败，第" + order + "次重连……");
								bootstrap.config().group().schedule(() -> connect(bootstrap, host, port, retry - 1), delay, TimeUnit
										.SECONDS);
						}
				});
		}

		private static void startConsoleThread(Channel channel) {
				ConsoleCommandManager consoleCommandManager = new ConsoleCommandManager();
				LoginConsoleCommand loginConsoleCommand = new LoginConsoleCommand();
				Scanner scanner = new Scanner(System.in);

				new Thread(() -> {
						while (!Thread.interrupted()) {
								if (!SessionUtil.hasLogin(channel)) {
										loginConsoleCommand.exec(scanner, channel);
								} else {
										consoleCommandManager.exec(scanner, channel);
								}
						}
				}).start();
		}

		private static void waitForLoginResponse() {
				try {
						Thread.sleep(1000);
				} catch (InterruptedException ignored) {
				}
		}

}

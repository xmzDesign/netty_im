package com.xmz.netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.nio
 * @class: NIOServer.java
 * @description:
 * @Date 2019-04-29 14:31
 */
public class NIOServer {

		public static void main(String[] args) throws IOException {
				Selector serverSelector = Selector.open();
				Selector clientSelector = Selector.open();

				new Thread(() -> {
						try {
								// 对应IO编程中服务端启动
								ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
								serverSocketChannel.socket().bind(new InetSocketAddress(8000));
								serverSocketChannel.configureBlocking(false);
								serverSocketChannel.register(serverSelector, SelectionKey.OP_ACCEPT);

								while (true) {
										// 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
										if (serverSelector.select(1) > 0) {
												Set<SelectionKey> selectionKeys = serverSelector.selectedKeys();
												Iterator<SelectionKey> iterator = selectionKeys.iterator();
												while (iterator.hasNext()) {
														SelectionKey key = iterator.next();
														if (key.isAcceptable()) {

																// (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
																try {
																		SocketChannel clienChannel = ((ServerSocketChannel) key.channel()).accept();
																		clienChannel.configureBlocking(false);
																		clienChannel.register(clientSelector, SelectionKey.OP_READ);
																} finally {
																		iterator.remove();
																}
														}
												}
										}
								}
						} catch (IOException e) {
								e.printStackTrace();
						}
				}).start();

				new Thread(() -> {
						try {
								while (true) {
										// (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
										if (clientSelector.select(1) > 0) {
												Set<SelectionKey> set = clientSelector.selectedKeys();
												Iterator<SelectionKey> keyIterator = set.iterator();

												while (keyIterator.hasNext()) {
														SelectionKey key = keyIterator.next();

														if (key.isReadable()) {
																try {
																		SocketChannel clientChannel = (SocketChannel) key.channel();
																		ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
																		// (3) 面向 Buffer
																		clientChannel.read(byteBuffer);
																		byteBuffer.flip();
																		System.out.println(Charset.defaultCharset().newDecoder().decode(byteBuffer)
																				.toString());
																} finally {
																		keyIterator.remove();
																		key.interestOps(SelectionKey.OP_READ);
																}
														}

												}
										}
								}
						} catch (IOException ignored) {
						}
				}).start();


		}

}

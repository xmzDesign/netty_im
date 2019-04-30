package com.xmz.netty.io;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty
 * @class: IOServer.java
 * @description: 传统io编程 服务端读取数据
 * @Date 2019-04-29 13:37
 */
public class IOServer {

		public static void main(String[] args) throws IOException {
				ServerSocket serverSocket = new ServerSocket(8000);
				new Thread(() -> {
						while (true) {
								try {
										// (1) 阻塞方法获取新的连接
										Socket accept = serverSocket.accept();
										// (2) 每一个新的连接都创建一个线程，负责读取数据
										new Thread(() -> {
												int len;
												byte[] bytes = new byte[1024];
												InputStream inputStream = null;
												try {
														inputStream = accept.getInputStream();
														while ((len = inputStream.read(bytes)) != -1) {
																System.out.println(new String(bytes, 0, len));
														}
												} catch (IOException e) {
														e.printStackTrace();
												}

										}).start();
								} catch (IOException e) {
										e.printStackTrace();
								}

						}
				}).start();
		}

}

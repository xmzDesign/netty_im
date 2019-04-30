package com.xmz.netty.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty
 * @class: IOClient.java
 * @description: io 客户端  每隔 2 秒，我们向服务端写一个带有时间戳的 "hello world"。
 * @Date 2019-04-29 13:45
 */
public class IOClient {

		public static void main(String[] args) {
				new Thread(()->{
						try {
								Socket socket = new Socket("127.0.0.1", 8000);
								while (true){
										socket.getOutputStream().write((new Date()+"").getBytes());
										Thread.sleep(2000);
								}
						} catch (IOException e) {
								e.printStackTrace();
						} catch (InterruptedException e) {
								e.printStackTrace();
						}
				}).start();
		}

}

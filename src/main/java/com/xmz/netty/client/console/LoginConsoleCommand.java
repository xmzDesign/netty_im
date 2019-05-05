package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: LoginConsoleCommand.java
 * @description:
 * @Date 2019-05-05 13:22
 */
public class LoginConsoleCommand implements ConsoleCommand {

		@Override
		public void exec(Scanner scanner, Channel channel) {
				LoginRequestPacket loginRequestPacket = new LoginRequestPacket();

				System.out.print("输入用户名登录: ");
				loginRequestPacket.setUsername(scanner.nextLine());
				loginRequestPacket.setPassword("pwd");

				// 发送登录数据包
				channel.writeAndFlush(loginRequestPacket);
				waitForLoginResponse();
		}

		private static void waitForLoginResponse() {
				try {
						Thread.sleep(1000);
				} catch (InterruptedException ignored) {
				}
		}
}


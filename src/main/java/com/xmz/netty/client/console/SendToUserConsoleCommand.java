package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.MessageRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: SendToUserConsoleCommand.java
 * @description:
 * @Date 2019-05-05 11:53
 */
public class SendToUserConsoleCommand implements ConsoleCommand {
		@Override
		public void exec(Scanner scanner, Channel channel) {
				System.out.print("发送消息给某个某个用户,用户id:");
				String toUserId = scanner.next();
				System.out.println("信息内容:");
				String message = scanner.next();
				channel.writeAndFlush(new MessageRequestPacket(toUserId, message));
		}
}

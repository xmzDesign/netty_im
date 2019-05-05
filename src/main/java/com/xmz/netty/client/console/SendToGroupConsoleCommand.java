package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.GroupMessageRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: SendToGroupConsoleCommand.java
 * @description:
 * @Date 2019-05-05 15:02
 */
public class SendToGroupConsoleCommand implements ConsoleCommand {
		@Override
		public void exec(Scanner scanner, Channel channel) {
				System.out.print("发送消息给某个某个群组：");

				String toGroupId = scanner.next();
				String message = scanner.next();
				channel.writeAndFlush(new GroupMessageRequestPacket(toGroupId, message));

		}
}

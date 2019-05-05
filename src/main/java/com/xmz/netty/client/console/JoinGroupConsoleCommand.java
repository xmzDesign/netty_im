package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.JoinGroupRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: JoinGroupConsoleCommand.java
 * @description:
 * @Date 2019-05-05 14:00
 */
public class JoinGroupConsoleCommand implements ConsoleCommand {
		@Override
		public void exec(Scanner scanner, Channel channel) {
				JoinGroupRequestPacket joinGroupRequestPacket = new JoinGroupRequestPacket();

				System.out.print("输入 groupId，加入群聊：");
				String groupId = scanner.next();

				joinGroupRequestPacket.setGroupId(groupId);
				channel.writeAndFlush(joinGroupRequestPacket);
		}
}

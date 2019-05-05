package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.QuitGroupRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: QuitGroupConsoleCommand.java
 * @description:
 * @Date 2019-05-05 14:03
 */
public class QuitGroupConsoleCommand implements ConsoleCommand {
		@Override
		public void exec(Scanner scanner, Channel channel) {
				QuitGroupRequestPacket quitGroupRequestPacket = new QuitGroupRequestPacket();

				System.out.print("输入 groupId，退出群聊：");
				String groupId = scanner.next();

				quitGroupRequestPacket.setGroupId(groupId);
				channel.writeAndFlush(quitGroupRequestPacket);
		}
}

package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.ListGroupMembersRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: ListGroupMembersConsoleCommand.java
 * @description:
 * @Date 2019-05-05 14:04
 */
public class ListGroupMembersConsoleCommand implements ConsoleCommand {

		@Override
		public void exec(Scanner scanner, Channel channel) {
				ListGroupMembersRequestPacket listGroupMembersRequestPacket = new ListGroupMembersRequestPacket();

				System.out.print("输入 groupId，获取群成员列表：");
				String groupId = scanner.next();

				listGroupMembersRequestPacket.setGroupId(groupId);
				channel.writeAndFlush(listGroupMembersRequestPacket);
		}
}

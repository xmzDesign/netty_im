package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.CreateGroupRequestPacket;
import io.netty.channel.Channel;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: CreateGroupConsoleCommand.java
 * @description:
 * @Date 2019-05-05 13:09
 */
public class CreateGroupConsoleCommand implements ConsoleCommand{
		private static final String USER_ID_SPLITER = ",";

		@Override
		public void exec(Scanner scanner, Channel channel) {
				CreateGroupRequestPacket createGroupRequestPacket = new CreateGroupRequestPacket();

				System.out.print("【拉人群聊】输入 userId 列表，userId 之间英文逗号隔开：");
				String userIds = scanner.next();
				createGroupRequestPacket.setUserIdList(Arrays.asList(userIds.split(USER_ID_SPLITER)));
				channel.writeAndFlush(createGroupRequestPacket);
		}

}

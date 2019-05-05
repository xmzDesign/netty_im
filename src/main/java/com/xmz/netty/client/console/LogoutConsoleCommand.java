package com.xmz.netty.client.console;

import com.xmz.netty.protocol.request.LogoutRequestPacket;
import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: LogoutConsoleCommand.java
 * @description:
 * @Date 2019-05-05 11:53
 */
public class LogoutConsoleCommand implements ConsoleCommand {
		@Override
		public void exec(Scanner scanner, Channel channel) {
				LogoutRequestPacket logoutRequestPacket = new LogoutRequestPacket();
				channel.writeAndFlush(logoutRequestPacket);
		}
}

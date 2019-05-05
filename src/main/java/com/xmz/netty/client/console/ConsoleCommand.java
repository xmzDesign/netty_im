package com.xmz.netty.client.console;

import io.netty.channel.Channel;
import java.util.Scanner;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.client.console
 * @class: ConsoleCommand.java
 * @description:
 * @Date 2019-05-05 11:51
 */
public interface ConsoleCommand {
		void exec(Scanner scanner, Channel channel);
}

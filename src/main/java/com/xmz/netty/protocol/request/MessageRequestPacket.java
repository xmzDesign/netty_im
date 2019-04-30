package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.MESSAGE_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: MessageRequestPacket.java
 * @description:
 * @Date 2019-04-30 13:32
 */
@Data
public class MessageRequestPacket extends Packet {
		private String message;

		@Override
		public Byte getCommand() {
				return MESSAGE_REQUEST;
		}
}

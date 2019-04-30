package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.MESSAGE_RESPONSE;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: MessageResponsePacket.java
 * @description:
 * @Date 2019-04-30 13:33
 */
@Data
public class MessageResponsePacket extends Packet {
		private String message;

		@Override
		public Byte getCommand() {
				return MESSAGE_RESPONSE;
		}

}

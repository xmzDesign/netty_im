package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.QUIT_GROUP_RESPONSE;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: QuitGroupResponsePacket.java
 * @description:
 * @Date 2019-05-05 14:05
 */
@Data
public class QuitGroupResponsePacket extends Packet {

		private String groupId;

		private boolean success;

		private String reason;

		@Override
		public Byte getCommand() {

				return QUIT_GROUP_RESPONSE;
		}
}

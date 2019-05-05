package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.JOIN_GROUP_RESPONSE;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: JoinGroupResponsePacket.java
 * @description:
 * @Date 2019-05-05 14:04
 */
@Data
public class JoinGroupResponsePacket extends Packet {
		private String groupId;

		private boolean success;

		private String reason;

		@Override
		public Byte getCommand() {

				return JOIN_GROUP_RESPONSE;
		}
}

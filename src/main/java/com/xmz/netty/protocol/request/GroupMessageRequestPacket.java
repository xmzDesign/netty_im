package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.GROUP_MESSAGE_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: GroupMessageRequestPacket.java
 * @description:
 * @Date 2019-05-05 14:42
 */
@Data
@NoArgsConstructor
public class GroupMessageRequestPacket extends Packet {
		private String toGroupId;
		private String message;

		public GroupMessageRequestPacket(String toGroupId, String message) {
				this.toGroupId = toGroupId;
				this.message = message;
		}

		@Override
		public Byte getCommand() {
				return GROUP_MESSAGE_REQUEST;
		}
}


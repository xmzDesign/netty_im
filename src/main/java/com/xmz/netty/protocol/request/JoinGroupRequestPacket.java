package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.JOIN_GROUP_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: JoinGroupRequestPacket.java
 * @description:
 * @Date 2019-05-05 14:00
 */
@Data
public class JoinGroupRequestPacket extends Packet {

		private String groupId;

		@Override
		public Byte getCommand() {

				return JOIN_GROUP_REQUEST;
		}
}

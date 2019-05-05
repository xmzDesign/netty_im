package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.GROUP_MESSAGE_RESPONSE;

import com.xmz.netty.protocol.Packet;
import com.xmz.netty.session.Session;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: GroupMessageResponsePacket.java
 * @description:
 * @Date 2019-05-05 14:44
 */
@Data
public class GroupMessageResponsePacket extends Packet {

		private String fromGroupId;

		private Session fromUser;

		private String message;

		@Override
		public Byte getCommand() {

				return GROUP_MESSAGE_RESPONSE;
		}
}

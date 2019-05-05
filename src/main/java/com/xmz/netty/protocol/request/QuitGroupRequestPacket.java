package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.QUIT_GROUP_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: QuitGroupRequestPacket.java
 * @description:
 * @Date 2019-05-05 14:02
 */
@Data
public class QuitGroupRequestPacket extends Packet {

		private String groupId;

		@Override
		public Byte getCommand() {

				return QUIT_GROUP_REQUEST;
		}
}

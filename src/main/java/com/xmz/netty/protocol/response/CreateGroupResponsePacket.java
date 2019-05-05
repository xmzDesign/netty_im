package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.CREATE_GROUP_RESPONSE;

import com.xmz.netty.protocol.Packet;
import java.util.List;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: CreateGroupResponsePacket.java
 * @description:
 * @Date 2019-05-05 13:16
 */
@Data
public class CreateGroupResponsePacket extends Packet {
		private boolean success;

		private String groupId;

		private List<String> userNameList;

		@Override
		public Byte getCommand() {

				return CREATE_GROUP_RESPONSE;
		}
}

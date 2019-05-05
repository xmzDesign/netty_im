package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: ListGroupMembersRequestPacket.java
 * @description:
 * @Date 2019-05-05 14:02
 */
@Data
public class ListGroupMembersRequestPacket extends Packet {

		private String groupId;

		@Override
		public Byte getCommand() {

				return LIST_GROUP_MEMBERS_REQUEST;
		}
}

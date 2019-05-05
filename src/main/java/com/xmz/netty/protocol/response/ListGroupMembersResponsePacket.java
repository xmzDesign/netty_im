package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;

import com.xmz.netty.protocol.Packet;
import com.xmz.netty.session.Session;
import java.util.List;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: ListGroupMembersResponsePacket.java
 * @description:
 * @Date 2019-05-05 14:05
 */
@Data
public class ListGroupMembersResponsePacket extends Packet {

		private String groupId;

		private List<Session> sessionList;

		@Override
		public Byte getCommand() {

				return LIST_GROUP_MEMBERS_RESPONSE;
		}
}

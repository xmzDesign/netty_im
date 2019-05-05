package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.CREATE_GROUP_REQUEST;

import com.xmz.netty.protocol.Packet;
import java.util.List;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: CreateGroupRequestPacket.java
 * @description:
 * @Date 2019-05-05 13:10
 */
@Data
public class CreateGroupRequestPacket extends Packet {

		private List<String> userIdList;

		@Override
		public Byte getCommand() {

				return CREATE_GROUP_REQUEST;
		}
}


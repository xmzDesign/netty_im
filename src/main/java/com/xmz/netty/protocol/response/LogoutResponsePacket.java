package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.LOGOUT_RESPONSE;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: LogoutResponsePacket.java
 * @description:
 * @Date 2019-05-05 13:16
 */
@Data
public class LogoutResponsePacket extends Packet {

		private boolean success;

		private String reason;


		@Override
		public Byte getCommand() {

				return LOGOUT_RESPONSE;
		}
}

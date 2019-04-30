package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.LOGIN_RESPONSE;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: LoginResponsePacket.java
 * @description:
 * @Date 2019-04-30 09:37
 */
@Data
public class LoginResponsePacket extends Packet {
		private boolean success;

		private String reason;

		@Override
		public Byte getCommand() {
				return LOGIN_RESPONSE;
		}
}

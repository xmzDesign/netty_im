package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.LOGOUT_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: LogoutRequestPacket.java
 * @description:
 * @Date 2019-05-05 11:55
 */
@Data
public class LogoutRequestPacket extends Packet {
		@Override
		public Byte getCommand() {

				return LOGOUT_REQUEST;
		}
}

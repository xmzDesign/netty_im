package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.LOGIN_REQUEST;

import com.xmz.netty.protocol.Packet;
import lombok.Data;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: LoginRequestPacket.java
 * @description: 请求包
 * @Date 2019-04-30 09:35
 */
@Data
public class LoginRequestPacket extends Packet {

		private String username;

		private String password;

		@Override
		public Byte getCommand() {
				return LOGIN_REQUEST;
		}
}

package com.xmz.netty.protocol.command;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.command
 * @class: Command.java
 * @description:
 * @Date 2019-04-30 09:32
 */
public interface Command {
		Byte LOGIN_REQUEST = 1;

		Byte LOGIN_RESPONSE = 2;

		Byte MESSAGE_REQUEST = 3;

		Byte MESSAGE_RESPONSE = 4;

		Byte LOGOUT_REQUEST = 5;

		Byte LOGOUT_RESPONSE = 6;

		Byte CREATE_GROUP_REQUEST = 7;

		Byte CREATE_GROUP_RESPONSE = 8;
}

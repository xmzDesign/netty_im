package com.xmz.netty.protocol.response;

import static com.xmz.netty.protocol.command.Command.HEARTBEAT_RESPONSE;

import com.xmz.netty.protocol.Packet;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.response
 * @class: HeartBeatResponsePacket.java
 * @description:
 * @Date 2019-05-05 16:04
 */
public class HeartBeatResponsePacket extends Packet {
		@Override
		public Byte getCommand() {
				return HEARTBEAT_RESPONSE;
		}
}

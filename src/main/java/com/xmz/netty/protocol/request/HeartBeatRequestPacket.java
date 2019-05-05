package com.xmz.netty.protocol.request;

import static com.xmz.netty.protocol.command.Command.HEARTBEAT_REQUEST;

import com.xmz.netty.protocol.Packet;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol.request
 * @class: HeartBeatRequestPacket.java
 * @description:
 * @Date 2019-05-05 16:03
 */
public class HeartBeatRequestPacket extends Packet {
		@Override
		public Byte getCommand() {
				return HEARTBEAT_REQUEST;
		}
}


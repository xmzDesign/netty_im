package com.xmz.netty.protocol;

import static com.xmz.netty.protocol.command.Command.CREATE_GROUP_REQUEST;
import static com.xmz.netty.protocol.command.Command.CREATE_GROUP_RESPONSE;
import static com.xmz.netty.protocol.command.Command.GROUP_MESSAGE_REQUEST;
import static com.xmz.netty.protocol.command.Command.GROUP_MESSAGE_RESPONSE;
import static com.xmz.netty.protocol.command.Command.JOIN_GROUP_REQUEST;
import static com.xmz.netty.protocol.command.Command.JOIN_GROUP_RESPONSE;
import static com.xmz.netty.protocol.command.Command.LIST_GROUP_MEMBERS_REQUEST;
import static com.xmz.netty.protocol.command.Command.LIST_GROUP_MEMBERS_RESPONSE;
import static com.xmz.netty.protocol.command.Command.LOGIN_REQUEST;
import static com.xmz.netty.protocol.command.Command.LOGIN_RESPONSE;
import static com.xmz.netty.protocol.command.Command.LOGOUT_REQUEST;
import static com.xmz.netty.protocol.command.Command.LOGOUT_RESPONSE;
import static com.xmz.netty.protocol.command.Command.MESSAGE_REQUEST;
import static com.xmz.netty.protocol.command.Command.MESSAGE_RESPONSE;
import static com.xmz.netty.protocol.command.Command.QUIT_GROUP_REQUEST;
import static com.xmz.netty.protocol.command.Command.QUIT_GROUP_RESPONSE;

import com.xmz.netty.protocol.request.CreateGroupRequestPacket;
import com.xmz.netty.protocol.request.GroupMessageRequestPacket;
import com.xmz.netty.protocol.request.JoinGroupRequestPacket;
import com.xmz.netty.protocol.request.ListGroupMembersRequestPacket;
import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.request.LogoutRequestPacket;
import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.request.QuitGroupRequestPacket;
import com.xmz.netty.protocol.response.CreateGroupResponsePacket;
import com.xmz.netty.protocol.response.GroupMessageResponsePacket;
import com.xmz.netty.protocol.response.JoinGroupResponsePacket;
import com.xmz.netty.protocol.response.ListGroupMembersResponsePacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.protocol.response.LogoutResponsePacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
import com.xmz.netty.protocol.response.QuitGroupResponsePacket;
import com.xmz.netty.serialize.Serializer;
import com.xmz.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.protocol
 * @class: PacketCodeC.java
 * @description:
 * @Date 2019-04-30 09:40
 */
public class PacketCodeC {

		public static final int MAGIC_NUM = 0x12345678;
		public static final PacketCodeC INSTANCE = new PacketCodeC();

		private final Map<Byte, Class<? extends Packet>> packetTypeMap;
		private final Map<Byte, Serializer> serializerMap;

		private PacketCodeC() {
				packetTypeMap = new HashMap<>();
				packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
				packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
				packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
				packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);
				packetTypeMap.put(LOGOUT_REQUEST, LogoutRequestPacket.class);
				packetTypeMap.put(LOGOUT_RESPONSE, LogoutResponsePacket.class);
				packetTypeMap.put(CREATE_GROUP_REQUEST, CreateGroupRequestPacket.class);
				packetTypeMap.put(CREATE_GROUP_RESPONSE, CreateGroupResponsePacket.class);
				packetTypeMap.put(JOIN_GROUP_REQUEST, JoinGroupRequestPacket.class);
				packetTypeMap.put(JOIN_GROUP_RESPONSE, JoinGroupResponsePacket.class);
				packetTypeMap.put(QUIT_GROUP_REQUEST, QuitGroupRequestPacket.class);
				packetTypeMap.put(QUIT_GROUP_RESPONSE, QuitGroupResponsePacket.class);
				packetTypeMap.put(LIST_GROUP_MEMBERS_REQUEST, ListGroupMembersRequestPacket.class);
				packetTypeMap.put(LIST_GROUP_MEMBERS_RESPONSE, ListGroupMembersResponsePacket.class);
				packetTypeMap.put(GROUP_MESSAGE_REQUEST, GroupMessageRequestPacket.class);
				packetTypeMap.put(GROUP_MESSAGE_RESPONSE, GroupMessageResponsePacket.class);



				serializerMap = new HashMap<>();
				Serializer serializer = new JSONSerializer();
				serializerMap.put(serializer.getSerializerAlogrithm(), serializer);
		}


//		public ByteBuf encode(ByteBufAllocator byteBufAllocator, Packet packet) {
//				// 1. 创建 ByteBuf 对象
//				ByteBuf byteBuf = byteBufAllocator.ioBuffer();
//				// 2. 序列化 java 对象
//				byte[] bytes = Serializer.DEFAULT.serialize(packet);
//				// 3. 实际编码过程
//				byteBuf.writeInt(MAGIC_NUM);
//				byteBuf.writeByte(packet.getVersion());
//				byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
//				byteBuf.writeByte(packet.getCommand());
//				byteBuf.writeInt(bytes.length);
//				byteBuf.writeBytes(bytes);
//				return byteBuf;
//		}


		public void encode(ByteBuf byteBuf, Packet packet) {
				// 1. 序列化 java 对象
				byte[] bytes = Serializer.DEFAULT.serialize(packet);

				// 2. 实际编码过程
				byteBuf.writeInt(MAGIC_NUM);
				byteBuf.writeByte(packet.getVersion());
				byteBuf.writeByte(Serializer.DEFAULT.getSerializerAlogrithm());
				byteBuf.writeByte(packet.getCommand());
				byteBuf.writeInt(bytes.length);
				byteBuf.writeBytes(bytes);
		}


		public Packet decode(ByteBuf byteBuf) {
				// 跳过 magic number
				byteBuf.skipBytes(4);

				// 跳过版本号
				byteBuf.skipBytes(1);

				// 序列化算法
				byte serializeAlgorithm = byteBuf.readByte();

				// 指令
				byte command = byteBuf.readByte();

				// 数据包长度
				int length = byteBuf.readInt();

				byte[] bytes = new byte[length];
				byteBuf.readBytes(bytes);

				Class<? extends Packet> requestType = getRequestType(command);
				Serializer serializer = getSerializer(serializeAlgorithm);

				if (requestType != null && serializer != null) {
						return serializer.deserialize(requestType, bytes);
				}

				return null;
		}

		private Serializer getSerializer(byte serializeAlgorithm) {

				return serializerMap.get(serializeAlgorithm);
		}

		private Class<? extends Packet> getRequestType(byte command) {

				return packetTypeMap.get(command);
		}


}

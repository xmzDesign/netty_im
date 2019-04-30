package com.xmz.netty.protocol;

import static com.xmz.netty.protocol.command.Command.LOGIN_REQUEST;
import static com.xmz.netty.protocol.command.Command.LOGIN_RESPONSE;
import static com.xmz.netty.protocol.command.Command.MESSAGE_REQUEST;
import static com.xmz.netty.protocol.command.Command.MESSAGE_RESPONSE;

import com.xmz.netty.protocol.request.LoginRequestPacket;
import com.xmz.netty.protocol.request.MessageRequestPacket;
import com.xmz.netty.protocol.response.LoginResponsePacket;
import com.xmz.netty.protocol.response.MessageResponsePacket;
import com.xmz.netty.serialize.Serializer;
import com.xmz.netty.serialize.impl.JSONSerializer;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
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

		private static final int MAGIC_NUM = 0x12345678;
		public static final PacketCodeC instance = new PacketCodeC();

		private final Map<Byte, Class<? extends Packet>> packetTypeMap;
		private final Map<Byte, Serializer> serializerMap;

		private PacketCodeC() {
				packetTypeMap = new HashMap<>();
				packetTypeMap.put(LOGIN_REQUEST, LoginRequestPacket.class);
				packetTypeMap.put(LOGIN_RESPONSE, LoginResponsePacket.class);
				packetTypeMap.put(MESSAGE_REQUEST, MessageRequestPacket.class);
				packetTypeMap.put(MESSAGE_RESPONSE, MessageResponsePacket.class);


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

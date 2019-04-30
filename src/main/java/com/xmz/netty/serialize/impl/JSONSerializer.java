package com.xmz.netty.serialize.impl;

import com.alibaba.fastjson.JSON;
import com.xmz.netty.serialize.Serializer;
import com.xmz.netty.serialize.SerializerAlogrithm;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.serialize.impl
 * @class: JSONSerializer.java
 * @description:
 * @Date 2019-04-30 09:43
 */
public class JSONSerializer implements Serializer {

		@Override
		public byte getSerializerAlogrithm() {
				return SerializerAlogrithm.JSON;
		}

		@Override
		public byte[] serialize(Object object) {
				return JSON.toJSONBytes(object);
		}

		@Override
		public <T> T deserialize(Class<T> clazz, byte[] bytes) {
				return JSON.parseObject(bytes,clazz);
		}
}

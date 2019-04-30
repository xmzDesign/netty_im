package com.xmz.netty.serialize;


import com.xmz.netty.serialize.impl.JSONSerializer;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.serialize
 * @class: Serializer.java
 * @description:
 * @Date 2019-04-30 09:42
 */
public interface Serializer {

		Serializer DEFAULT = new JSONSerializer();

		/**
		 * 序列化算法
		 * @return
		 */
		byte getSerializerAlogrithm();

		/**
		 * java 对象转换成二进制
		 */
		byte[] serialize(Object object);

		/**
		 * 二进制转换成 java 对象
		 */
		<T> T deserialize(Class<T> clazz, byte[] bytes);


}

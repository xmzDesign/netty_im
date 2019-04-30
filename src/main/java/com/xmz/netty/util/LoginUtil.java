package com.xmz.netty.util;

import com.xmz.netty.attribute.Attributes;
import io.netty.channel.Channel;
import io.netty.util.Attribute;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.util
 * @class: LoginUtil.java
 * @description:
 * @Date 2019-04-30 13:36
 */
public class LoginUtil {
		public static void markAsLogin(Channel channel) {
				channel.attr(Attributes.LOGIN).set(true);
		}

		public static boolean hasLogin(Channel channel) {
				Attribute<Boolean> loginAttr = channel.attr(Attributes.LOGIN);

				return loginAttr.get() != null;
		}
}

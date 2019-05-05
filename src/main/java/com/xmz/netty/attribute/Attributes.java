package com.xmz.netty.attribute;

import com.xmz.netty.session.Session;
import io.netty.util.AttributeKey;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.attribute
 * @class: Attributes.java
 * @description:
 * @Date 2019-04-30 13:36
 */
public interface Attributes {
		AttributeKey<Boolean> LOGIN = AttributeKey.newInstance("login");

		AttributeKey<Session> SESSION = AttributeKey.newInstance("session");
}

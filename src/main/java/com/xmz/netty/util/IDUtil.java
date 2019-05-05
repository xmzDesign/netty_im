package com.xmz.netty.util;

import java.util.UUID;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.util
 * @class: IDUtil.java
 * @description:
 * @Date 2019-05-05 13:25
 */
public class IDUtil {
		public static String randomId() {
				return UUID.randomUUID().toString().split("-")[0];
		}

}

package com.xmz.netty.session;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Xu.Minzhe
 * @version V1.0
 * @package com.xmz.netty.session
 * @class: Session.java
 * @description:
 * @Date 2019-05-05 10:21
 */
@Data
@NoArgsConstructor
public class Session {

		private String userId;
		private String userName;

		public Session(String userId, String userName) {
				this.userId = userId;
				this.userName = userName;
		}

		@Override
		public String toString() {
				return userId + ":" + userName;
		}

}

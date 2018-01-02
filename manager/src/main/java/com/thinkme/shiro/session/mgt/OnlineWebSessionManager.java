package com.thinkme.shiro.session.mgt;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 * 为OnlineSession定制的Web Session Manager
 * 主要是在此如果会话的属性修改了 就标识下其修改了 然后方便 OnlineSessionDao同步
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/02 下午5:29
 */
public class OnlineWebSessionManager extends DefaultWebSessionManager {
}

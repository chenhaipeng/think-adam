package com.thinkme.shiro.session.mgt;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.SessionContext;
import org.apache.shiro.session.mgt.SessionFactory;

/**
 * 创建自定义的session，
 * 添加一些自定义的数据
 * 如 用户登录到的系统ip
 * 用户状态（在线 隐身 强制退出）
 * 等 比如当前所在系统等
 *
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/02 下午5:48
 */
public class OnlineSessionFactory implements SessionFactory {

    @Override
    public Session createSession(SessionContext sessionContext) {
        return null;
    }
}

package com.thinkme.config;

import org.apache.shiro.ShiroException;
import org.apache.shiro.util.Initializable;
import org.springframework.stereotype.Component;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/03 上午12:28
 */
@Component
public class TestBean implements Initializable {
    @Override
    public void init() throws ShiroException {
        System.out.println("init --------bean-------");
    }
}

package com.thinkme.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2017/11/25 下午10:18
 */
@RestController
public class IndexController {
    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value = "/")
    public String index() {
//      System.out.println("hello world");
        logger.info("hello chen");
        return "hello thinkme";
    }

    @RequestMapping(value = "/jsp")
    public ModelAndView index(ModelMap map) {
        map.put("title", "HelloWorld");
        return new ModelAndView("index");
    }
}

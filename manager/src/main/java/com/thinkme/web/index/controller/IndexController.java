package com.thinkme.web.index.controller;

import com.thinkme.base.bind.annotation.CurrentUser;
import com.thinkme.sys.resource.service.ResourceService;
import com.thinkme.sys.resource.vo.Menu;
import com.thinkme.sys.user.entity.User;
import com.thinkme.utils.json.FastJsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/26 下午7:46
 */
@Controller("adminIndexController")
@RequestMapping("/admin")
@Slf4j
public class IndexController {

    @Autowired
    ResourceService resourceService;

    @RequestMapping(value = "/index")
    public String index(@CurrentUser User user, Model model) {

        List<Menu> menus = resourceService.findMenus(user);
        model.addAttribute("menus", menus);
        log.info("用户：{},加载菜单：{}",user.getUsername(), FastJsonUtil.object2String(menus));
//        pushApi.offline(user.getId());

        return "admin/index/index";
    }
}

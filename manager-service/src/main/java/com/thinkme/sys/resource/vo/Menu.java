package com.thinkme.sys.resource.vo;

import com.thinkme.utils.base.BeanUtils;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 界面是那个使用的菜单对象
 */
@Data
public class Menu implements Serializable {
    private Long id;
    private String name;
    private String icon;
    private String url;

    private Long pid;

    private List<Menu> children = new ArrayList<>();

    public boolean isHasChildren() {
        return !BeanUtils.isEmpty(children);
    }

    public Menu(Long id, String name, String icon, String url, Long pid) {
        this.id = id;
        this.name = name;
        this.icon = icon;
        this.url = url;
        this.pid = pid;
    }
}

package com.thinkme.sys.group.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午4:53
 */
@Table("sys_group")
@Data
public class Group implements Serializable {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("type")
    private GroupType type;

    @Column("default_group")
    private Boolean defaultGroup = Boolean.FALSE;

    @Column("is_show")
    private Boolean show = Boolean.FALSE;
}

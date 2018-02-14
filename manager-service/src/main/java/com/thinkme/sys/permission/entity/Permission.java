package com.thinkme.sys.permission.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 22:24:42
 */
@Table("sys_permission")
@Data
public class Permission implements Serializable {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("permission")
    private String permission;
    @Column("description")
    private String description;
    @Column("is_show")
    private Integer isShow;

}
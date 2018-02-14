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
@Table("sys_role")
@Data
public class Role implements Serializable {
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("role")
    private String role;
    @Column("description")
    private String description;
    @Column("is_show")
    private Boolean isShow;

}
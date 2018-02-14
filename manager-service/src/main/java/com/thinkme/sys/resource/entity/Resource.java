package com.thinkme.sys.resource.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-25 14:33:20
 */
@Table("sys_resource")
@Data
public class Resource implements Serializable{
    @Id
    @Column("id")
    private Long id;
    @Column("name")
    private String name;
    @Column("identity")
    private String identity;
    @Column("url")
    private String url;
    @Column("parent_id")
    private Long parentId;
    @Column("parent_ids")
    private String parentIds;
    @Column("icon")
    private String icon;
    @Column("weight")
    private String weight;
    @Column("is_show")
    private Boolean isShow;

}
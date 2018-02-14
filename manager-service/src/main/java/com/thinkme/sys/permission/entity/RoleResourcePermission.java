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
@Table("sys_role_resource_permission")
@Data
public class RoleResourcePermission implements Serializable {
    @Id
    @Column("id")
    private Long id;
    @Column("role_id")
    private Long roleId;
    @Column("resource_id")
    private Long resourceId;
    @Column("permission_ids")
    private String permissionIds;

}
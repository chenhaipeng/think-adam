package com.thinkme.sys.user.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * @author chenhaipeng
 * @version 1.0
 * @date 2018-01-24 15:50:01
 */
@Table("sys_auth")
@Data
public class Auth implements Serializable {
    @Id
    @Column("id")
    private Long id;
    @Column("organization_id")
    private Long organizationId;
    @Column("job_id")
    private Long jobId;
    @Column("user_id")
    private Long userId;
    @Column("group_id")
    private Long groupId;
    @Column("role_ids")
    private String roleIds;
    @Column("type")
    private String type;

}
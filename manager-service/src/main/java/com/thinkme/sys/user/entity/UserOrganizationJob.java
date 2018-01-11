package com.thinkme.sys.user.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午12:53
 */
@Table("sys_user_organization_job")
@Data
public class UserOrganizationJob implements Serializable {
    @Id
    private Long id;

    @Column("user_id")
    private Long userId;

    @Column("organization_id")
    private Long organizationId;

    @Column("job_id")
    private Long jobId;


}

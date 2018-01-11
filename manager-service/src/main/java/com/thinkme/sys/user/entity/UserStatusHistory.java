package com.thinkme.sys.user.entity;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 下午4:31
 */
@Table("sys_user_status_history")
@Data
public class UserStatusHistory implements Serializable {

    @Id
    private Long id;

    /**
     * 锁定的用户
     */
    @Column("user_id")
    private String userId;

    /**
     * 备注信息
     */
    @Column("reason")
    private String reason;

    /**
     * 操作的状态
     */
    @Column("status")
    private UserStatus status;

    /**
     * 操作的管理员
     */
    @Column("op_user_id")
    private String opUserId;

    /**
     * 操作时间
     */
    @Column("op_date")
    private Date opDate;

}

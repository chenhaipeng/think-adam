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
 * @date 2018/01/11 下午11:51
 */
@Table("sys_group_relation")
@Data
public class GroupRelation implements Serializable {

    @Id
    private Long id;

    @Column("group_id")
    private Long groupId;

    @Column("organization_id")
    private Long organizationId;

    /**
     * 关联的单个用户
     */
    @Column("user_id")
    private Long userId;

    /**
     * 关联的 区间user id 作为单个关联的一种优化
     * 和user二者选一
     * [startUserId, endUserId]闭区间
     */
    @Column("start_user_id")
    private Long startUserId;

    @Column("end_user_id")
    private Long endUserId;
}

package com.thinkme.framework.base.model;

import lombok.Data;
import org.nutz.dao.entity.annotation.Column;

import java.io.Serializable;
import java.util.Date;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/09 上午10:05
 */
@Data
public abstract class BaseModel implements Serializable {

    @Column("version")
    private int version;

    @Column("create_time")
    private Date createTime;

    @Column("update_time")
    private Date updateTime;

}

package com.thinkme.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;

import java.io.Serializable;

/**
 * 演示实体父类
 */
public class SuperEntity<T extends Model> extends Model<T> {

    /**
     * 主键ID
     */
    @TableId("test_id")
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}

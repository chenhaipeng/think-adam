package com.thinkme.sys.organization.entity;

import com.thinkme.framework.utils.SpringUtils;
import com.thinkme.plugin.entity.Treeable;
import com.thinkme.sys.organization.service.JobService;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/11 下午12:24
 */
@Table("sys_job")
@Data
public class Job implements Treeable {

    @Id
    private Long id;

    @Column("name")
    private String name;

    @Column("parent_id")
    private Long parentId;

    @Column("parent_ids")
    private String parentIds;

    @Column("icon")
    private String icon;

    @Column("weight")
    private Integer weight;

    @Column("is_show")
    private Boolean show = Boolean.FALSE;


    @Override
    public String getSeparator() {
        return "/";
    }

    @Override
    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
    }

    @Override
    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    @Override
    public boolean isRoot() {
        if (getParentId() != null && getParentId() == 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean isLeaf() {
        if (isRoot()) {
            return false;
        }
        if (isHasChildren()) {
            return false;
        }

        return true;
    }

    @Override
    public boolean isHasChildren() {
        JobService jobService = SpringUtils.getBean("jobService");
        return jobService.isHasChildren(this.id);
    }

    @Override
    public String getRootDefaultIcon() {
        return "ztree_root_open";
    }

    @Override
    public String getBranchDefaultIcon() {
        return "ztree_branch";
    }

    @Override
    public String getLeafDefaultIcon() {
        return "ztree_leaf";
    }
}

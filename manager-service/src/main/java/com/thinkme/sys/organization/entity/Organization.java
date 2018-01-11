package com.thinkme.sys.organization.entity;

import com.thinkme.framework.utils.SpringUtils;
import com.thinkme.plugin.entity.Treeable;
import com.thinkme.sys.organization.service.OrganizationService;
import lombok.Data;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * @author chenhaipeng
 * @version 1.0
 * @mail donotcoffee@gmail.com
 * @date 2018/01/10 下午4:40
 */
@Table("sys_organization")
@Data
public class Organization implements Treeable {
    @Id
    private Long id;

    @Column("name")
    private String name;

    //    组织机构类型
    @Column("type")
    private OrganizationType type;

    @Column("parent_id")
    private Long parentId;

    @Column("parent_Ids")
    private String parentIds;

    @Column("weight")
    private Integer weight;

    /**
     * 图标
     */
    @Column("icon")
    private String icon;


    private boolean hasChildren;

    @Column("is_show")
    private Boolean show = Boolean.FALSE;

    public static void main(String[] args) {
        System.out.println(new Organization().isHasChildren());

    }

    @Override
    public String getSeparator() {
        return "/";
    }

    @Override
    public String makeSelfAsNewParentIds() {
        return getParentIds() + getId() + getSeparator();
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

    public boolean isHasChildren() {
        OrganizationService organizationService = SpringUtils.getBean("organizationService");
        return organizationService.isHasChildren(this.id);
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

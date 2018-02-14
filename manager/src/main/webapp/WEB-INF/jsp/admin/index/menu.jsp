<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- sidebar menu: : style can be found in sidebar.less -->
<ul class="sidebar-menu" data-widget="tree">
    <c:forEach items="${menus}" var="m">
        <li class="treeview">
            <a href="#"><i class="fa fa-link"></i> <span>${m.name}</span>
                <span class="pull-right-container">
                <i class="fa fa-angle-left pull-right"></i>
                </span>
            </a>
            <ul class="treeview-menu">
                <c:forEach items="${m.children}" var="c">

                    <ad:submenu menu="${c}"/>

                </c:forEach>
            </ul>
        </li>

    </c:forEach>
</ul>
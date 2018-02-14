<%@tag pageEncoding="UTF-8" description="构建子菜单" %>
<%@ attribute name="menu" type="com.thinkme.sys.resource.vo.Menu " required="true" description="当前菜单" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="ad" tagdir="/WEB-INF/tags" %>

<c:choose>
    <c:when test="${!menu.hasChildren}">
        <li><a href="<%=menuUrl(request, menu.getUrl())%>"><i class="fa fa-circle-o"></i>${menu.name}</a></li>
    </c:when>
    <c:otherwise>
        <ul class="treeview-menu">
            <a href="#">${menu.name}</a>
            <c:forEach items="${menu.children}" var="menu2">
                <%--<li><a href="<%=menuUrl(request, menu.getUrl())%>"><i class="fa fa-circle-o"></i>${menu.name}</a></li>--%>
                <ad:submenu menu="${menu2}"/>
                
            </c:forEach>
        </ul>
    </c:otherwise>
</c:choose>

<%!
    private static String menuUrl(HttpServletRequest request, String url) {
        if (url.startsWith("http")) {
            return url;
        }
        String ctx = request.getContextPath();

        if (url.startsWith(ctx) || url.startsWith("/" + ctx)) {
            return url;
        }

        if (!url.startsWith("/")) {
            url = url + "/";
        }
        return ctx + url;

    }
%>


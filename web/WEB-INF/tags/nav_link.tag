<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag pageEncoding="UTF-8"%>
<%@attribute name="title" required="true"%>
<%@attribute name="link"%>
<%@attribute name="module" required="true" type="java.lang.Integer"%>
<%--<c:if test="${active_user.hasModuleAccess(module)}">--%>
	<li class="nav-menu-head">
		<a href="${root_path}system/${link}">${title}</a>
	</li>
<%--</c:if>--%>
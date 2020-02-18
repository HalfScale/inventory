<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag pageEncoding="UTF-8"%>
<%@attribute name="title" type="java.lang.String" required="true"%>
<%@attribute name="pagetag" type="java.lang.String"%>
<%@attribute name="link"%>
<%@attribute name="sprite"%>
<%@attribute name="module" required="true" type="java.lang.Integer"%>
<c:if test="${active_user.hasModuleAccess(module)}">
	<li class="nav-menu-head <c:if test="${pagetag.equalsIgnoreCase(title)}">navlink-active</c:if>">	
		<a href="${root_path}${link}" class="sys-sidenav-link">
			<i class="fas fa-${sprite} fa-2x"></i>
			<span>${title}</span>
		</a>
	</li>
</c:if>
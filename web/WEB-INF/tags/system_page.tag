<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<%@attribute name="pagetag" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="top_nav_link" fragment="true" %>
<%@attribute name="post_body" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <title>${title}</title>
        <t:system_head/>
        <jsp:invoke fragment="head"/>
    </head>

    <body>
        <t:system_sidenav pagetag="${pagetag}">
            <jsp:attribute name="top_nav">
				<jsp:invoke fragment="top_nav_link"/>
            </jsp:attribute>
            <jsp:attribute name="content">
                <jsp:doBody/>
            </jsp:attribute>
        </t:system_sidenav>
        <jsp:invoke fragment="post_body"/>
		<script src="${root_path}/assets/js/avatar.js"></script>
    </body>
</html>

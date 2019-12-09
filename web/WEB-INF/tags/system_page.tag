<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="title" %>
<%@attribute name="head" fragment="true" %>
<%@attribute name="post_body" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
    <head>
        <title>${title}</title>
        <t:system_head/>
        <jsp:invoke fragment="head"/>
    </head>

    <body>
        <t:system_sidenav>
            <jsp:attribute name="content">
                <jsp:doBody/>
            </jsp:attribute>
        </t:system_sidenav>
        <jsp:invoke fragment="post_body"/>
    </body>
</html>

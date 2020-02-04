<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Home">
	
	<jsp:attribute name="post_body">
        <script src="assets/js/dashboard.js"></script>
    </jsp:attribute>
		
    <jsp:body>
        <div class="progress">
			<div class="progress-bar progress-bar-striped progress-bar-animated" role="progressbar" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100" style="width: 100%">
				Saving...
			</div>
		</div>
    </jsp:body>
</t:system_page>

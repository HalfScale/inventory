<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Home">
	
	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="assets/css/style.css"/>
    </jsp:attribute>
	
	<jsp:attribute name="post_body">
        <script src="assets/js/dashboard.js"></script>
    </jsp:attribute>
		
    <jsp:body>
		<div id="dashboard">
			<div id="dashboard-container" class="d-flex">
<!--				<div class="dashboard-item">
					<span id="totalInventoryProductsLabel"></span>
				</div>
				<div class="dashboard-item">

				</div>
				
				<div class="dashboard-item">

				</div>-->
			</div>
		</div>
		
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<div class="dashboard-item">
						<span id="totalInventoryProductsLabel"></span>
						<div class="dashboard-footer"></div>
					</div>
				</div>
				<div class="col-sm">
					<div class="dashboard-item">
						<div class="dashboard-footer"></div>
					</div>
				</div>
				<div class="col-sm">
					<div class="dashboard-item">
						<div class="dashboard-footer"></div>
					</div>
				</div>
			</div>
		</div>
    </jsp:body>
</t:system_page>

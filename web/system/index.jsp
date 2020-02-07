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
		<!--Grid-->
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<div class="dashboard-item shadow-lg rounded">
						<div class="dashboard-item-label">Total Products</div>
						<div id="totalInventoryProductsLabel" class="dashboard-item-detail"></div>
						<div class="dashboard-footer"></div>
					</div>
				</div>
				<div class="col-sm">
					<div class="dashboard-item shadow-lg rounded">
						<div class="dashboard-item-label">Total Sold Products</div>
						<div id="totalSoldProductsLabel" class="dashboard-item-detail"></div>
						<div class="dashboard-footer"></div>
					</div>
				</div>
				<div class="col-sm">
					<div class="dashboard-item shadow-lg rounded">
						<div class="dashboard-footer"></div>
						<div class="dashboard-item-label">Total User</div>
						<div id="totalUsers" class="dashboard-item-detail"></div>
					</div>
				</div>
			</div>
		</div>
		
		<section class="table-label-header">
			<span class="border-bottom">Recent Activity</span>
		</section>

		<!--Table-->
		<table id="dashBoardTable" class="table table-hover w-100">
			<thead class="thead-dark">
				<tr>
					<th scope="col">User</th>
					<th scope="col">Timestamp</th>
					<th scope="col">Action</th>
					<th scope="col">Product</th>
					<th scope="col">Qty</th>
					<th scope="col">Price</th>
				</tr>
			</thead>

			<tbody>

			</tbody>
		</table>
    </jsp:body>
</t:system_page>

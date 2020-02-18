<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<!DOCTYPE html>
<t:system_page title="Dashboard" pagetag="dashboard">

	<jsp:attribute name="head">
		<link rel="stylesheet" type="text/css" href="assets/css/style.css"/>
    </jsp:attribute>

	<jsp:attribute name="top_nav_link">
		<n:curr title="Dashboard"/>
	</jsp:attribute>

	<jsp:attribute name="post_body">
        <script src="assets/js/dashboard.js"></script>
        <script src="assets/js/dashboard.profile.js"></script>
    </jsp:attribute>

    <jsp:body>
		<main>
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
		</main>

		<section id="userProfile" class="hidden-widget">
			<content class="d-flex justify-content-center">
				<form id="userProfileForm">
					<div class="some-form d-flex flex-column">
						<img id="user-avatar"src="${root_path}avatar" alt="" class="img-thumbnail">
						<div class="input-group">
							<div class="custom-file">
								<input type="file" class="custom-file-input" name="image" id="profileImageInput" accept="image/png, image/jpeg" required>
								<label class="custom-file-label" for="profileImageInput">Choose file</label>
							</div>
						</div>
						<button type="submit" class="btn btn-primary">Save</button>
						<button id="profileReturn" type="button" class="btn btn-secondary">Return</button>
					</div>
				</form>
			</content>
		</section>
    </jsp:body>
</t:system_page>

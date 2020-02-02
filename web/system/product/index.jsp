<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<t:system_page title="Product">
	<jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css"/>
    </jsp:attribute>

	<jsp:attribute name="top_nav_link">
		<n:prev title="Home" link="../"/>
		<n:curr title="Product"/>
	</jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/product.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="bttn-section-top">
            <button id="productAddBtn" type="button" class="btn btn-outline-dark">Add Product</button>
        </div>

		<!--TABLE-->
        <table id="productTable" class="table table-hover w-100">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Code</th>
                    <th scope="col">Name</th>
                    <th scope="col">Price</th>
                    <th scope="col">Stock</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>
            <tbody>

            </tbody>
        </table>

		<!--MODALS-->
        <div id="productAddModal" class="hidden-widget">
			<form>
				<div class="form-group">
					<label for="">Name</label>
					<input type="text" class="name form-control" name="name" required>
				</div>

				<div class="form-group">
					<label for="">Code</label>
					<input type="text" class="code form-control text-uppercase" name="code" required>
				</div>

				<div class="form-group">
					<label for="">Brand</label>
					<select class="brand custom-select" name="brand" required>
						<option selected disabled>Select Brand</option>
					</select>
				</div>

				<div class="form-group">
					<label for="">Category</label>
					<select class="category custom-select" name="category" required>
						<option selected disabled>Select Category</option>
					</select>
				</div>

				<div class="form-group">
					<label for="">Description</label>
					<textarea class="description form-control" name="description"></textarea>
				</div>

				<div class="form-group">
					<label for="">Price</label>
					<input type="text" class="price form-control" name="price" required>
				</div>

				<div class="form-group">
					<label for="">Reseller Price</label>
					<input type="text" class="resellerPrice form-control" name="resellerPrice" required>
				</div>

				<div class="form-group">
					<label for="">Stock</label>
					<input type="text" class="stock form-control" name="stock" required>
				</div>

				<div class="form-group">
					<label for="">Status</label>
					<select class="status custom-select" name="status" required>
						<option selected disabled>Select Status</option>
						<option value="1">Active</option>
						<option value="0">Inactive</option>
					</select>
				</div>

				<input type="submit" class="dummy-submit">
			</form>
        </div>

		<div id="productUpdateModal" class="hidden-widget">
			<form>
				<div class="hidden-widget">
					<input type="text" class="id" name="id">
				</div>

				<div class="form-group">
					<label for="">Name</label>
					<input type="text" class="name form-control" name="name" required>
				</div>

				<div class="form-group">
					<label for="">Code</label>
					<input type="text" class="code form-control text-uppercase" name="code" required>
				</div>

				<div class="form-group">
					<label for="">Brand</label>
					<select class="brand custom-select" name="brand" required>
						<option selected disabled>Select Brand</option>
					</select>
				</div>

				<div class="form-group">
					<label for="">Category</label>
					<select class="category custom-select" name="category" required>
						<option selected disabled>Select Category</option>
					</select>
				</div>

				<div class="form-group">
					<label for="">Description</label>
					<textarea class="description form-control" name="description"></textarea>
				</div>

				<div class="form-group">
					<label for="">Price</label>
					<input type="text" class="price form-control" name="price" required>
				</div>

				<div class="form-group">
					<label for="">Reseller Price</label>
					<input type="text" class="resellerPrice form-control" name="resellerPrice" required>
				</div>

				<div class="form-group">
					<label for="">Stock</label>
					<input type="text" class="stock form-control" name="stock" required>
				</div>

				<div class="form-group">
					<label for="">Status</label>
					<select class="status custom-select" name="status" required>
						<option selected disabled>Select Status</option>
						<option value="1">Active</option>
						<option value="0">Inactive</option>
					</select>
				</div>

				<input type="submit" class="dummy-submit">
			</form>
		</div>
    </jsp:body>
</t:system_page>

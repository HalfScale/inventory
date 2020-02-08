<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<!DOCTYPE html>
<t:system_page title="Category">
	<jsp:attribute name="head">

    </jsp:attribute>
	
	<jsp:attribute name="top_nav_link">
        <n:prev title="Dashboard" link="../"/>
        <n:curr title="Category"/>
    </jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/category.js"></script>
    </jsp:attribute>

    <jsp:body>
		<!--Button section-->
		<div class="bttn-section-top">
            <button id="categoryAddBttn" type="button" class="btn btn-outline-dark">Add Category</button>
        </div>

		<!--Table-->
        <table id="categoryTable" class="table table-hover w-100">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Status</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>

		<!--Modal-->
		<div id="categoryAddModal" class="hidden-widget">
			<form>
				<div class="form-group">
					<label for="">Name</label>
					<input type="text" class="name form-control" name="name" required>
				</div>

				<div class="form-group">
					<label for="">Status</label>
					<select class="status custom-select" name="status" required>
						<option selected disabled>Select status</option>
						<option value="1">Active</option>
						<option value="0">Inactive</option>
					</select>
				</div>

				<input type="submit" class="dummy-submit">
			</form>
		</div>

		<div id="categoryUpdateModal" class="hidden-widget">
			<form>
				<div class="hidden-widget">
					<input type="text" class="id" name="id">
				</div>

				<div class="form-group">
					<label for="">Name</label>
					<input type="text" class="name form-control" name="name" required>
				</div>

				<div class="form-group">
					<label for="">Status</label>
					<select class="status custom-select" name="status" required>
						<option selected disabled>Select status</option>
						<option value="1">Active</option>
						<option value="0">Inactive</option>
					</select>
				</div>

				<input type="submit" class="dummy-submit">
			</form>
		</div>
    </jsp:body>
</t:system_page>

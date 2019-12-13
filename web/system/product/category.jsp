<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Category">
	<jsp:attribute name="head">

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
                    <th scope="col">ID</th>
                    <th scope="col">Name</th>
                    <th scope="col">Active</th>
                    <th scope="col">Action</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>

		<!--Modal-->
		<div id="categoryAddModal">
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
		
		<div id="" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Add Category</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<form>
							<div class="form-group">
								<label for="">Name</label>
								<input type="text" class="form-control" name="name" required>
							</div>
							<div class="form-group">
								<select class="custom-select" name="status" required>
									<option selected disabled>Select status</option>
									<option value="active">Active</option>
									<option value="inactive">Inactive</option>
								</select>
							</div>

							<input type="submit" class="dummy-submit">
						</form>
					</div>

					<div class="modal-footer">
						<button id="brandModalClose" class="btn btn-secondary" type="button">close</button>
						<button id="brandModalSave" class="btn btn-primary" type="button">Save changes</button>
					</div>
				</div>
			</div>
		</div>
    </jsp:body>
</t:system_page>

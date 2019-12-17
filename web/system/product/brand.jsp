<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Brand">
	<jsp:attribute name="head">

    </jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/brand.js"></script>
    </jsp:attribute>

    <jsp:body>
		<!--Button section-->
		<div class="bttn-section-top">
            <button id="brandAddBttn" type="button" class="btn btn-outline-dark">Add Brand</button>
        </div>

		<!--Table-->
        <table id="brandTable" class="table table-hover w-100">
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
		<div id="brandAddModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Add Brand</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
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

					<div class="modal-footer">
						<button id="brandAddClose" class="btn btn-secondary" type="button" data-dismiss="modal">close</button>
						<button id="brandAddSave" class="btn btn-primary" type="button">Save changes</button>
					</div>
				</div>
			</div>
		</div>

		<div id="brandUpdateModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Update Brand</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
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

					<div class="modal-footer">
						<button id="brandUpdateClose" class="btn btn-secondary" type="button" data-dismiss="modal">close</button>
						<button id="brandUpdateSave" class="btn btn-primary" type="button">Save changes</button>
					</div>
				</div>
			</div>
		</div>

		<div id="brandDeleteModal" class="modal fade" tabindex="-1" role="dialog">
			<div class="modal-dialog" role="document">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title">Delete Brand</h5>
						<button class="close" type="button" data-dismiss="modal" aria-label="close">
							<span aria-hidden="true">&times;</span>
						</button>
					</div>

					<div class="modal-body">
						<span>Do you really want to delete this entry?</span>
						<form>
							<div class="hidden-widget">
								<input type="text" class="id" name="id">
							</div>

							<input type="submit" class="dummy-submit">
						</form>
					</div>

					<div class="modal-footer">
						<button id="brandDeleteClose" class="btn btn-secondary" type="button" data-dismiss="modal">close</button>
						<button id="brandDeleteSave" class="btn btn-primary" type="button">Confirm</button>
					</div>
				</div>
			</div>
		</div>
    </jsp:body>
</t:system_page>

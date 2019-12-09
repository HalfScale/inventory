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
            <button id="brandAddBttn" type="button" class="btn btn-outline-dark">Add Category</button>
        </div>
		
		<!--Table-->
        <table class="table table-hover">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <th scope="row">1</th>
                    <td>Mark</td>
                    <td>Otto</td>
                    <td>@mdo</td>
                </tr>
                <tr>
                    <th scope="row">2</th>
                    <td>Jacob</td>
                    <td>Thornton</td>
                    <td>@fat</td>
                </tr>
                <tr>
                    <th scope="row">3</th>
                    <td>Larry</td>
                    <td>the Bird</td>
                    <td>@twitter</td>
                </tr>
            </tbody>
        </table>
		
		<!--Modal-->
		<div id="categoryModal" class="modal fade" tabindex="-1" role="dialog">
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

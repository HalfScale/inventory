<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Role">
    <jsp:attribute name="head">

    </jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/role.js"></script>
    </jsp:attribute>

    <jsp:body>
        <!--Button section-->
        <div class="bttn-section-top">
            <button id="roleAddBttn" type="button" class="btn btn-outline-dark">Add Role</button>
        </div>

        <!--Table-->
        <table id="roleTable" class="table table-hover w-100">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Modules</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>

        <!--Modal-->
        <div id="roleAddModal">
            <form>
                <div class="form-group">
                    <label for="">Name</label>
                    <input type="text" class="form-control" name="name" required>
                </div>

                <div id="moduleSelection">
					<h5><span class="badge badge-primary">Select Modules</span></h5>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="user" value="100">
						<label class="custom-control-label" for="user">User</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="role" value="101">
						<label class="custom-control-label" for="role">Role</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="product" value="200"> 
						<label class="custom-control-label" for="product">Product</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="category" value="201">
						<label class="custom-control-label" for="category">Category</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="brand" value="202">
						<label class="custom-control-label" for="brand">Brand</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="pos" value="300">
						<label class="custom-control-label" for="pos">POS</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="transaction" value="400">
						<label class="custom-control-label" for="transaction">Transaction</label>
					</div>
                </div>


                <input type="submit" class="dummy-submit">
            </form>
        </div>

        <!--<div id="roleUpdateModal">
            <form>
                <div class="hidden-widget">
                    <input type="text" class="id" name="id">
                </div>

                <div class="form-group">
                    <label for="">First Name</label>
                    <input type="text" class="firstName form-control" name="firstName" required>
                </div>

                <div class="form-group">
                    <label for="">Last Name</label>
                    <input type="text" class="lastName form-control" name="lastName" required>
                </div>

                <div class="form-group">
                    <label for="">Email</label>
                    <input type="email" class="email form-control" name="email" required>
                </div>
				
                <div class="form-group">
                    <label for="">Username</label>
                    <input type="text" class="username form-control" name="username" required>
                </div>

                <div class="form-group">
                    <label for="">Password</label>
                    <input type="password" class="password form-control" name="password">
                </div>
				
                <div class="form-group">
                    <label for="">Confirm Password</label>
                    <input type="password" class="confirmPassword form-control" name="confirmPassword">
                </div>

                <div class="form-group">
                    <label for="">Status</label>
                    <select class="status custom-select" name="status">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>

                <input type="submit" class="dummy-submit">
            </form>
        </div>-->
    </jsp:body>
</t:system_page>
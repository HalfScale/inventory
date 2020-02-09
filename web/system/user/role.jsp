<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<!DOCTYPE html>
<t:system_page title="Role" pagetag="role">
    <jsp:attribute name="head">

    </jsp:attribute>
	
	<jsp:attribute name="top_nav_link">
        <n:prev title="Dashboard" link="../"/>
        <n:curr title="Role"/>
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
        <div id="roleAddModal" class="hidden-widget">
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

        <div id="roleUpdateModal" class="hidden-widget">
            <form>
                <div class="hidden-widget">
                    <input type="text" class="id" name="id">
                </div>
				
				<div class="form-group">
                    <label for="">Name</label>
                    <input type="text" class="name form-control" name="name" required>
                </div>
				
				<div id="moduleSelection">
					<h5><span class="badge badge-primary">Select Modules</span></h5>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="userUpdate" value="100">
						<label class="custom-control-label" for="userUpdate">User</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="roleUpdate" value="101">
						<label class="custom-control-label" for="roleUpdate">Role</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="productUpdate" value="200"> 
						<label class="custom-control-label" for="productUpdate">Product</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="categoryUpdate" value="201">
						<label class="custom-control-label" for="categoryUpdate">Category</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="brandUpdate" value="202">
						<label class="custom-control-label" for="brandUpdate">Brand</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="posUpdate" value="300">
						<label class="custom-control-label" for="posUpdate">POS</label>
					</div>
					<div class="custom-control custom-switch">
						<input type="checkbox" class="moduleSwitch custom-control-input" id="transactionUpdate" value="400">
						<label class="custom-control-label" for="transactionUpdate">Transaction</label>
					</div>
                </div>

                <input type="submit" class="dummy-submit">
            </form>
        </div>
    </jsp:body>
</t:system_page>
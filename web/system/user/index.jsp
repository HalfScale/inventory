<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="User">
    <jsp:attribute name="head">

    </jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/user.js"></script>
    </jsp:attribute>

    <jsp:body>
        <!--Button section-->
        <div class="bttn-section-top">
            <button id="userAddBttn" type="button" class="btn btn-outline-dark">Add User</button>
        </div>

        <!--Table-->
        <table id="userTable" class="table table-hover w-100">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">Name</th>
                    <th scope="col">Email</th>
                    <th scope="col">Status</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>

        <!--Modal-->
        <div id="userAddModal">
            <form>
                <div class="form-group">
                    <label for="">First Name</label>
                    <input type="text" class="form-control" name="firstName" required>
                </div>
                
                <div class="form-group">
                    <label for="">Last Name</label>
                    <input type="text" class="form-control" name="lastName" required>
                </div>
                
                <div class="form-group">
                    <label for="">Email</label>
                    <input type="email" class="form-control" name="email" required>
                </div>
                
                <div class="form-group">
                    <label for="">Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>
                
                <div class="form-group">
                    <label for="">Confirm Password</label>
                    <input type="password" class="form-control" name="password" required>
                </div>

                <div class="form-group">
                    <label for="">Status</label>
                    <select class="custom-select" name="status">
                        <option value="1">Active</option>
                        <option value="0">Inactive</option>
                    </select>
                </div>

                <input type="submit" class="dummy-submit">
            </form>
        </div>

        <div id="userUpdateModal">
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
                    <label for="">Password</label>
                    <input type="password" class="password form-control" name="password">
                </div>
                
                <div class="form-group">
                    <label for="">Confirm Password</label>
                    <input type="password" class="password form-control" name="password">
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
        </div>
    </jsp:body>
</t:system_page>
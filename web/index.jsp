<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link type="text/css" rel="stylesheet" href="assets/bootstrap-4.3.1-dist/css/bootstrap.min.css"/>
        <link type="text/css" rel="stylesheet" href="assets/css/style.css"/>
        
        <script src="assets/js/jQuery/jquery-3.4.1.min.js"></script>
        <script src="assets/bootstrap-4.3.1-dist/js/bootstrap.bundle.min.js"></script>
        <script src="assets/js/util.js"></script>
        <title>Login</title>
    </head>
    <body>
        
        <form class="form-signin">
            <div class="text-center mb-4">
                <h1 class="h3 mb-3 font-weight-normal sign-in-header">Sign In</h1>
                <p></p>
            </div>

            <div class="form-label-group">
                <input type="text" id="inputUser" class="form-control" placeholder="Email address" name="user" required autofocus>
                <label for="inputUser">User</label>
            </div>

            <div class="form-label-group">
                <input type="password" id="inputPassword" class="form-control" name="pass" placeholder="Password" required>
                <label for="inputPassword">Password</label>
            </div>
			
			<div id="statusBox" class="" role="alert">
				
			</div>

            <button class="btn btn-lg btn-primary btn-block" type="submit">
				Sign in
			</button>
        </form>
        
		<script src="assets/js/login.js"></script>
    </body>
</html>



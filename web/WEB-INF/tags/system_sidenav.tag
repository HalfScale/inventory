<%@tag description="System Side Navigation" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="content" fragment="true" required="true" %>
<%@attribute name="top_nav" fragment="true" required="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="wrapper">

    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
			<img src="${root_path}avatar" class="img-thumbnail img-fluid user-avatar" alt="User Picture">
        </div>

        <ul class="list-unstyled components">
            <p>${active_user.firstName} ${active_user.lastName}</p>
			<t:nav_link title="Dashboard" sprite="chart-line" module="-1"/>
			<t:nav_link title="User" sprite="user" module="100" link="user/" />
			<t:nav_link title="Role" sprite="users" module="101" link="user/role.jsp" />
			<t:nav_link title="Product" sprite="boxes" module="200" link="product/" />
			<t:nav_link title="Brand" sprite="tag" module="201" link="product/brand.jsp" />
			<t:nav_link title="Category" sprite="tags" module="202" link="product/category.jsp" />
			<t:nav_link title="POS" sprite="cash-register" module="300" link="pos/" />
			<t:nav_link title="Transaction" sprite="chart-bar" module="400" link="transaction/" />
            <li>
                <a class="sys-sidenav-link" href="#">Logout</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div id="content" class="w-100 p-3">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">
                <button type="button" id="sidebarCollapse" class="btn btn-info">
					<span class="fa fa-bars"></span>
                </button>

                <nav id="top-nav" class="d-flex align-items-center" aria-label="breadcrumb">
                    <ol class="breadcrumb">
						<jsp:invoke fragment="top_nav"/>
<!--                        <li class="breadcrumb-item"><a href="#">Home</a></li>-->
<!--                        <li class="breadcrumb-item"><a href="#">Library</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Data</li>-->
                    </ol>
                </nav>
            </div>
        </nav>
        <jsp:invoke fragment="content"/>
    </div>

</div>  
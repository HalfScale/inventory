<%@tag description="System Side Navigation" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="content" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="wrapper">

    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
			<img src="${root_path}assets/img/default.png" class="img-thumbnail img-fluid" alt="User Picture">
        </div>

        <ul class="list-unstyled components">
            <p>${active_user.firstName} ${active_user.lastName}</p>
            <li class="active">
                <a href="#homeSubmenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Dash Board</a>
                <ul class="collapse list-unstyled" id="homeSubmenu">
                    <li>
                        <a href="#">Home 1</a>
                    </li>
                    <li>
                        <a href="#">Home 2</a>
                    </li>
                    <li>
                        <a href="#">Home 3</a>
                    </li>
                </ul>
            </li>
			<t:nav_link title="User" module="100" link="user/" />
			<t:nav_link title="Role" module="101" link="user/role.jsp" />
			<t:nav_link title="Product" module="200" link="product/" />
			<t:nav_link title="Brand" module="201" link="product/brand.jsp" />
			<t:nav_link title="Category" module="202" link="product/category.jsp" />
			<t:nav_link title="POS" module="300" link="pos/" />
			<t:nav_link title="Transaction" module="400" link="transaction/" />
            <li>
                <a href="#">Logout</a>
            </li>
        </ul>
    </nav>

    <!-- Page Content -->
    <div id="content" class="w-100 p-3">
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container-fluid">

                <button type="button" id="sidebarCollapse" class="btn btn-info">
                    <i class="fas fa-align-left"></i>
                    <span>Toggle Sidebar</span>
                </button>

                <nav id="top-nav" class="d-flex align-items-center" aria-label="breadcrumb">
                    <ol class="breadcrumb">
                        <li class="breadcrumb-item"><a href="#">Home</a></li>
                        <li class="breadcrumb-item"><a href="#">Library</a></li>
                        <li class="breadcrumb-item active" aria-current="page">Data</li>
                    </ol>
                </nav>
            </div>
        </nav>

        <jsp:invoke fragment="content"/>
    </div>

</div>  
<%@tag description="System Side Navigation" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="content" fragment="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="wrapper">

    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
            <h3>User Picture</h3>
        </div>

        <ul class="list-unstyled components">
            <p>User Name</p>
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
            <li>
                <a href="#">Users</a>
            </li>
            <li>
                <a href="/Inventory/system/product/">Product</a>
            </li>
            <li>
                <a href="/Inventory/system/product/brand.jsp">Brand</a>
            </li>
            <li>
                <a href="/Inventory/system/product/category.jsp">Category</a>
            </li>
            <li>
                <a href="/Inventory/system/pos/">POS</a>
            </li>
            <li>
                <a href="#transacMenu" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Transaction</a>
                <ul class="collapse list-unstyled" id="transacMenu">
                    <li>
                        <a href="#">Type</a>
                    </li>
                </ul>
            </li>
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
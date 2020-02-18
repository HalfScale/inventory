<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@tag description="System Side Navigation" pageEncoding="UTF-8"%>

<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="title"%>
<%@attribute name="pagetag" type="java.lang.String"%>
<%@attribute name="content" fragment="true" required="true" %>
<%@attribute name="top_nav" fragment="true" required="true" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>

<div class="wrapper">
	<c:out value=""/>
    <!-- Sidebar -->
    <nav id="sidebar">
        <div class="sidebar-header">
			<img src="${root_path}avatar" class="img-thumbnail img-fluid user-avatar clicky" alt="User Picture">
        </div>

        <ul class="list-unstyled components">
            <p>${active_user.firstName} ${active_user.lastName}</p>
			<t:nav_link title="Dashboard" pagetag="${pagetag}" sprite="chart-line" module="-1" link="system/"/>
			<t:nav_link title="User" pagetag="${pagetag}" sprite="user" module="100" link="system/user/"/>
			<t:nav_link title="Role" pagetag="${pagetag}" sprite="users" module="101" link="system/user/role.jsp" />
			<t:nav_link title="Product" pagetag="${pagetag}" sprite="boxes" module="200" link="system/product/"/>
			<t:nav_link title="Brand" pagetag="${pagetag}" sprite="tag" module="201" link="system/product/brand.jsp" />
			<t:nav_link title="Category" pagetag="${pagetag}" sprite="tags" module="202" link="system/product/category.jsp" />
			<t:nav_link title="POS" pagetag="${pagetag}" sprite="cash-register" module="300" link="system/pos/"/>
			<t:nav_link title="Transaction" pagetag="${pagetag}" sprite="chart-bar" module="400" link="system/transaction/" />
			<t:nav_link title="Logout" sprite="sign-out-alt" module="-1" link="logout" />
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
                    </ol>
                </nav>
            </div>
        </nav>
        <jsp:invoke fragment="content"/>
    </div>

</div>  
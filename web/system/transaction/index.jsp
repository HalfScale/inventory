<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<!DOCTYPE html>
<t:system_page title="Transaction">
	<jsp:attribute name="head">
		<!--<link rel="stylesheet" type="text/css" href="assets/css"/>-->
    </jsp:attribute>
		
    <jsp:attribute name="top_nav_link">
        <n:prev title="Dashboard" link="../"/>
        <n:curr title="Transaction"/>
    </jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/transaction.js"></script>
    </jsp:attribute>

    <jsp:body>
		<!--Table-->
        <table id="transactionTable" class="table table-hover w-100">
            <thead class="thead-dark">
                <tr>
                    <th scope="col">User</th>
                    <th scope="col">Transaction Type</th>
                    <th scope="col">Timestamp</th>
                </tr>
            </thead>

            <tbody>

            </tbody>
        </table>
		
		<!--Dialogs-->
		<div id="transactionDetailDialog" class="hidden-widget">
			<table id="transactionDetailTable" class="table table-hover w-100">
				<thead class="thead-dark">
					<tr>
						<th scope="col">Product</th>
						<th scope="col" class="text-center">Brand</th>
						<th scope="col" class="text-center">Category</th>
						<th scope="col" class="text-center">Quantity</th>
						<th scope="col" class="text-right">Price</th>
						<th scope="col" class="text-right">Reseller Price</th>
						<th scope="col" class="text-center">Is Reseller</th>
					</tr>
				</thead>

				<tbody>

				</tbody>
				
				<tfoot>
					<tr>
						<td class="font-weight-bold" scope="col">Total Amount: <span id="transactionDetailTotal" class="text-warning">10000.00</span></td>
						<td colspan="6"scope="col"></th>
					</tr>
				</tfoot>
			</table>
		</div>
    </jsp:body>
</t:system_page>

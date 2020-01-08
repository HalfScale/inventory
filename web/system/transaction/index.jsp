<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<!DOCTYPE html>
<t:system_page title="Transaction">
	<jsp:attribute name="head">
		<!--<link rel="stylesheet" type="text/css" href="assets/css"/>-->
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
    </jsp:body>
</t:system_page>

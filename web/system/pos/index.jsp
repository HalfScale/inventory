<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="n" tagdir="/WEB-INF/tags/topnav" %>
<!DOCTYPE html>
<t:system_page title="POS" pagetag="pos">
    <jsp:attribute name="head">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css"/>
    </jsp:attribute>

	<jsp:attribute name="top_nav_link">
		<n:prev title="Dashboard" link="../"/>
		<n:curr title="POS"/>
	</jsp:attribute>

    <jsp:attribute name="post_body">
        <script src="assets/js/pos.js"></script>
    </jsp:attribute>

    <jsp:body>
        <div class="posContent d-flex">
            <div id="posProductTableWrapper" class="w-65">
                <table id="posProductTable" class="table table-hover w-100">
                    <thead class="thead-dark">
                        <tr>
                            <th scope="col">Name</th>
                            <th scope="col">Stock</th>
                            <th scope="col">Price</th>
                            <th scope="col">Reseller Price</th>
                        </tr>
                    </thead>

                    <tbody>

                    </tbody>
                </table>

            </div>

            <div id="pos-checkout" class="rounded shadow-sm w-35">
                <section id="pos-checkout-header" class="text-center">
                    Checkout
                </section>

                <section id="pos-checkout-content" class="overflow-auto h-75">
                    <table id="checkOutTable" class="table table-sm table-hover w-100">
                        <colgroup>
                            <col>
                            <col>
                            <col>
                        </colgroup>
                        <thead class="">
                            <tr>
                                <th scope="col">Name</th>
                                <th scope="col">Qty</th>
                                <th scope="col">Price</th>
                            </tr>
                        </thead>

                        <tbody>

                        </tbody>
                    </table>
                </section>

                <section id="pos-checkout-footer" class="position-relative">
                    <div class="text-center">
                        <span id="checkoutTotal">0.00</span>
                    </div>
                    <button id="posCheckOutBttn" class="btn btn-primary w-100">Checkout</button>
                </section>
            </div>
        </div>
		
		<!--Dialogs-->
        <div id="posCheckoutDialog" class="hidden-widget">
            <form>
                <div class="form-group">
                    <label for="">Total</label>
                    <input type="text" class="total form-control" readonly>
                </div>
                <div class="form-group">
                    <label for="">Transaction Type</label>
                    <select id="checkoutTransactionType" class="transactionType custom-select" name="transactionType" required>
                    </select>
                </div>

                <input type="submit" class="dummy-submit">
            </form>
        </div>


    </jsp:body>
</t:system_page>

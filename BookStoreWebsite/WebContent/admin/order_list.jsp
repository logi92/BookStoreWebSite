<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Orders - Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">Book Orders Management</h2>
	</div>
	<!--============================================-->
	<div class="message" align="center">
		<h4>${message}</h4>
	</div>
	<!--============================================-->
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>OrderId</th>
				<th>Ordered By</th>
				<th>Book Copies</th>
				<th>Total</th>
				<th>Payment Method</th>
				<th>Status</th>
				<th>Order Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listAllOrder}" varStatus="status">>
			<tr>
					<td>${status.index +1}</td>
					<td>${order.orderId}</td>
					<td>${order.customer.fullname}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber  currencySymbol="$" value="${order.orderTotal}" type="currency"/></td>
					<td>${order.paymentMethod}</td>
					<td>${order.orderStatus}</td>
					<td> <fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${order.orderDate}" /> </td>
					<td>
						<a href="view_order?id=${order.orderId}">Details</a> 
						<a href="edit_order?id=${order.orderId}">Edit</a> 
						<a href="javascript:void(0);" class="deleteLink" id="${order.orderId}">Delete</a>
					</td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<!--============================================-->
	<br />
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
		$(document).ready(function() {
			$(".deleteLink").each(function() {
				$(this).on("click",function() {
					orderId = $(this).attr("id");
						if (confirm('Are you sure you want to delete the Book Order with ID: '+ orderId+ ' ?')) {
								window.location = 'delete_order?id='+ orderId;
						}
					});
				});
			});
	</script>
</body>
</html>
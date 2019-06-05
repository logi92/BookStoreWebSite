<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>My Order History - Bookstore Administration</title>
<link rel="stylesheet" href="css/style.css" />
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">My Order History</h2>
	</div>
	<!--============================================-->
	<c:if test="${fn:length(listOrders) == 0}">
		<div  align="center">
			<h3>You have not placed any orders</h3>
		</div>
	</c:if>
	<!--============================================-->
	<c:if test="${fn:length(listOrders) > 0}">
		<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>Order ID</th>
				<th>Quantity</th>
				<th>Total Amount</th>
				<th>Order Date</th>
				<th>Status</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="order" items="${listOrders}" varStatus="status">>
			<tr>
					<td>${status.index +1}</td>
					<td>${order.orderId}</td>
					<td>${order.bookCopies}</td>
					<td><fmt:formatNumber  currencySymbol="$" value="${order.orderTotal}" type="currency"/></td>
					<td> <fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${order.orderDate}" /> </td>
					<td>${order.orderStatus}</td>
					<td><a href="show_order_detail?id=${order.orderId}">View Details</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
</c:if>
	<!--============================================-->
	<br />
	<jsp:include page="footer.jsp" />
</body>
</html>
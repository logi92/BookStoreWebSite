<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Order Details - Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">Details of Order ID : ${order.orderId}</h2>
	</div>
	<!--============================================-->
	<div class="message" align="center">
		<h4>${message}</h4>
	</div>
	<!--============================================-->
	<div align="center">
		<h2>Order Overview:</h2>
		<table border="1">
			<tr>
				<td><b>Ordered By:</b></td>
				<td>${order.customer.fullname}</td>
			</tr>
			<tr>
				<td><b>Book Copies:</b></td>
				<td>${order.bookCopies}</td>
			</tr>
			<tr>
				<td><b>Total Amount:</b></td>
				<td><fmt:formatNumber  currencySymbol="$" value="${order.orderTotal}" type="currency"/></td>
			</tr>
			<tr>
				<td><b>Recipient Name:</b></td>
				<td>${order.recipientName}</td>
			</tr>
			<tr>
				<td><b>Recipient Phone:</b></td>
				<td>${order.recipientPhone}</td>
			</tr>
			<tr>
				<td><b>Payment Method:</b></td>
				<td>${order.paymentMethod}</td>
			</tr>
			<tr>
				<td><b>Shipping Address:</b></td>
				<td>${order.shippingAddress}</td>
			</tr>
			<tr>
				<td><b>Order Status:</b></td>
				<td>${order.orderStatus}</td>
			</tr>
			<tr>
				<td><b>Order Date:</b></td>
				<td><fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${order.orderDate}" /></td>
			</tr>
		</table>
	</div>
	<div align="center">
		<h2>Ordered Books:</h2>
		<table border="1">
			<tr>
				<th>Index</th>
				<th>Book Title</th>
				<th>Author</th>
				<th>Price</th>
				<th>Quantity</th>
				<th>Sub Total</th>
			</tr>
			<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>
						<img style="vertical-align: middle;" src="data:image/jpg;base64,${orderDetail.book.base64Image}" width="48" height="64" />
						${orderDetail.book.title}
					</td>
					<td>${orderDetail.book.author}</td>
					<td>${orderDetail.book.price}</td>
					<td>${orderDetail.quantity}</td>
					<td><fmt:formatNumber  currencySymbol="$" value="${orderDetail.subTotal}" type="currency"/></td>
				</tr>
			</c:forEach>
			<tr>
			<td colspan="4" align="right"><b><i>TOTAL:</i></b></td>
			<td><b>${order.bookCopies}</b></td>
			<td><b><fmt:formatNumber  currencySymbol="$" value="${order.orderTotal}" type="currency"/></b></td>
			</tr>
		</table>
	</div>
	<div align="center">
	<br/>
	<a href="edit_order?id=${order.orderId}">Edit this Order</a>
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<a href="">Delete this Order</a>
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
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Order Details - Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">Edit Order ID : ${order.orderId}</h2>
	</div>
	<!--============================================-->
	<div class="message" align="center">
		<h4>${message}</h4>
	</div>
	<!--============================================-->
	<form action="update_order" method="post" id="orderForm">
		<div align="center">
			<table>
				<tr>
					<td><b>Ordered By:</b></td>
					<td>${order.customer.fullname}</td>
				</tr>
				<tr>
					<td><b>Order Date:</b></td>
					<td><fmt:formatDate pattern="MM/dd/yyyy HH:mm" value="${order.orderDate}" /></td>
				</tr>
				<tr>
					<td><b>Recipient Name:</b></td>
					<td><input type="text" name ="recipientName" value="${order.recipientName}" size="25"/></td>
				</tr>
				<tr>
					<td><b>Recipient Phone:</b></td>
					<td><input type="text" name ="recipientPhone" value="${order.recipientPhone}" size="25"/></td>
				</tr>
				<tr>
				<td><b>Shipping To:</b></td>
					<td><input type="text" name ="shippingAddress" value="${order.shippingAddress}" size="25"/></td>
				</tr>
				<tr>
					<td><b>Payment Method:</b></td>
					<td>
						<select name="paymentMethod">
							<option value="Cash On Delivery">${order.paymentMethod}</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><b>Order Status:</b></td>
					<td>
						<select name="orderStatus">
							<option value="Processing">Processing</option>
							<option value="Shipping">Shipping</option>
							<option value="Delivered">Delivered</option>
							<option value="Completed">Completed</option>
							<option value="Cancelled">Cancelled</option>
						</select>
					</td>
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
					<th>SubTotal</th>
					<th></th>
				</tr>
				<c:forEach items="${order.orderDetails}" var="orderDetail" varStatus="status">
					<tr>
						<td>${index.status + 1}</td>
						<td>${orderDetail.book.title}</td>
						<td>${orderDetail.book.author}</td>
						<td>${orderDetail.book.price}</td>
						<td><input type="text" name="quantity" value="${orderDetail.quantity}" size="5"/></td>
						<td><fmt:formatNumber  currencySymbol="$" value="${orderDetail.subTotal}" type="currency"/></td>
						<td><a href="">Remove</a></td>
					</tr>
				</c:forEach>
				<tr>
					<td colspan="4" align="right"><b><i>TOTAL:</i></b></td>
					<td><b>${order.bookCopies}</b></td>
					<td><b><fmt:formatNumber  currencySymbol="$" value="${order.orderTotal}" type="currency"/></b></td>
					<td></td>
				</tr>
			</table>
		</div>
		<div align="center">
			<br/>
				<a href="">Add Books</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="Save"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="Cancel"/>
		</div>
		<!--============================================-->
		<br />
	</form>
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
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
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
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
							<option value="Processing" <c:if test="${order.orderStatus eq 'Processing'}">selected='selected'</c:if> >Processing</option>
							<option value="Shipping" <c:if test="${order.orderStatus eq 'Shipping'}">selected='selected'</c:if> >Shipping</option>
							<option value="Delivered" <c:if test="${order.orderStatus eq 'Delivered'}">selected='selected'</c:if> >Delivered</option>
							<option value="Completed" <c:if test="${order.orderStatus eq 'Completed'}">selected='selected'</c:if> >Completed</option>
							<option value="Cancelled" <c:if test="${order.orderStatus eq 'Cancelled'}">selected='selected'</c:if> >Cancelled</option>
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
						<td>
							<input type="hidden" name="price" value="${orderDetail.book.price}" /> 
							<fmt:formatNumber  currencySymbol="$" value="${orderDetail.book.price}" type="currency"/>
						</td>
						<td>
							<input type="hidden" name="bookId" value="${orderDetail.book.bookId}" /> 
							<input type="text" name="quantity${status.index+1}" value="${orderDetail.quantity}" size="5"/>
						</td>
						<td><fmt:formatNumber  currencySymbol="$" value="${orderDetail.subTotal}" type="currency"/></td>
						<td><a href="remove_book_from_order?id=${orderDetail.book.bookId}">Remove</a></td>
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
				<a href="javascript:showAddBookPopup()">Add Books</a>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="submit" value="Save"/>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="button" value="Cancel" onclick="javascript:window.location.href='list_orders';"/>
		</div>
		<!--============================================-->
		<br />
	</form>
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
		function showAddBookPopup(){
			var width = 600;
			var height = 200;
			var left = (screen.width - width) / 2;
			var top = (screen.height - height) / 2;
			
			window.open('add_book_form',' _blank', 'width=' + width + ', height=' + height + ', top=' + top +', left=' + left );
		}
		
		$(document).ready(function() {
			$("#orderForm").validate({
				rules : {
					recipientName : "required",
					recipientPhone : "required",
					shippingAddress : "required",
					
					<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index + 1}: {
							required:true, number:true, min :1
						},
					</c:forEach>
				},
				messages : {
					recipientName : "Please Enter Recipient Name",
					recipientPhone : "Please Enter Reccipient Phone",
					shippingAddress : "Please Enter Shipping Address",
					
					<c:forEach items="${order.orderDetails}" var="book" varStatus="status">
						quantity${status.index + 1}: {
							required: "Please Enter Quantity",
							number: "Must be Number",
							min: "Must be more than 0"
						},
					</c:forEach>
				}
			});
		});
	</script>
</body>
</html>
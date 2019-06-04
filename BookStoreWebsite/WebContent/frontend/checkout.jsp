<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Checkout - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">

		<c:if test="${message!=null}">
			<div align="center">
				<h4 class="message">${message}</h4>
			</div>
		</c:if>

		<c:set var="cart" value="${sessionScope['cart']}" />

		<c:if test="${cart.totalItems==0}">
			<h2>There's no items in your cart</h2>
		</c:if>

		<c:if test="${cart.totalItems>0}">
				<div>
				<h2>Review Your Order Details <a href="view_cart">Edit</a></h2>
					<table border="1">
					<tr>
						<th>No</th>
						<th colspan="2">Book</th>
						<th>Author</th>
						<th>Price</th>
						<th>Quantity</th>
						<th>Subtotal</th>
					</tr>
					<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td><img class="book-small"	src="data:image/jpg;base64,${item.key.base64Image}" /></td>
								<td>&nbsp;&nbsp; <span id="book-title">${item.key.title}</span></td>
								<td>${item.key.author}</td>
								<td><fmt:formatNumber value="${item.key.price}"	type="currency" currencySymbol="$" /></td>
								<td align="center">${item.value}</td>
								<td><fmt:formatNumber value="${item.value * item.key.price}" type="currency" currencySymbol="$" /></td>
							</tr>
						</c:forEach>

						<tr>
							<td colspan="5" align="right"><b><i>Total:</i></b></td>
							<td align="center"><b>${cart.totalQuantity}</b></td>
							<td><b><fmt:formatNumber value="${cart.totalAmount}" type="currency" currencySymbol="$" /></b></td>
						</tr>
					</table>
					<h2>Your Shipping Information</h2>
				<form action="place_order" method="post" id="orderForm">
					<table>
						<tr>
							<td>Recipient Name:</td>
							<td><input type="text" name="recipientName" value="${loggedCustomer.fullname}"/></td>
						</tr>
						<tr>
							<td>Recipient Phone:</td>
							<td><input type="text" name="phone" value="${loggedCustomer.phoneNumber}"/></td>
						</tr>
						<tr>
							<td>Streed Address:</td>
							<td><input type="text" name="address" value="${loggedCustomer.address}"/></td>
						</tr>
						<tr>
							<td>City:</td>
							<td><input type="text" name="city" value="${loggedCustomer.city}"/></td>
						</tr>
						<tr>
							<td>Zip Code:</td>
							<td><input type="text" name="zipCode" value="${loggedCustomer.zipCode}"/></td>
						</tr>
						<tr>
							<td>Country:</td>
							<td><input type="text" name="country" value="${loggedCustomer.country}"/></td>
						</tr>
					</table>
					<div>
						<h2>Payment</h2>
						Choose your payment method: 
						&nbsp;&nbsp;&nbsp;
						<select name="paymentMethod">
							<option value="Cash On Delivery">Cash On Delivery</option>
						</select>
					</div>
					<div>
						<table class="normal">
							<tr>
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td></td>
								<td><button type="submit">Place Order</button></td>
								<td><a href="${pageContext.request.contextPath}/">Continue Shopping</a></td>
							</tr>
						</table>
					</div>
				</form>
			</div>
				
		</c:if>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#orderForm").validate({
			rules : {
				recipientName : "required",
				phone : "required",
				address : "required",
				city : "required",
				zipCode : "required",
				country : "required",
			},
			
			messages : {
				recipientName : "Please Enter Recipient Name",
				phone : "Please Enter Phone Number",
				address : "Please Enter Street Address",
				city : "Please Enter City",
				zipCode : "Please Enter Zip Code",
				country : "Please Enter Country",
			}
		});
	});
</script>
</html>
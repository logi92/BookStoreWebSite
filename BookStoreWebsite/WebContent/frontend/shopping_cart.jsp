<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Shopping Cart</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2>Your Shopping Cart Details</h2>

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
				<form action="">
					<table border="1">
						<tr>
							<th>No</th>
							<th colspan="2">Book</th>
							<th>Quantity</th>
							<th>Price</th>
							<th>Subtotal</th>
							<th><a href=""><b>Clear Cart</b></a></th>
						</tr>
						<c:forEach items="${cart.items}" var="item" varStatus="status">
							<tr>
								<td>${status.index+1}</td>
								<td>
								<img class="book-small" src="data:image/jpg;base64,${item.key.base64Image}"/>
								</td>
								<td>
								&nbsp;&nbsp;
								<span id="book-title">${item.key.title}</span>
								</td>
								<td>${item.value}</td>
								<td><fmt:formatNumber value="${item.key.price}"
										type="currency" /></td>
								<td><fmt:formatNumber
										value="${item.value * item.key.price}" type="currency" /></td>
								<td><a href="">Remove</a></td>
							</tr>
						</c:forEach>

						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td><b>${cart.totalQuantity} book(s)</b></td>
							<td>Total:</td>
							<td colspan="2"><fmt:formatNumber value="${cart.totalAmount}"
									type="currency" /></td>
						</tr>
					</table>
				</form>
			</div>
		</c:if>
	</div>

	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#loginForm").validate({
			rules : {
				email : {
					required : true,
					email : true,
				},
				password : "required",
			},
			messages : {
				email : {
					required : "Please Enter Email",
					email : "Please Enter an Valid Email address",
				},
				password : "Please Enter Password",
			},
		});
	});
</script>
</html>
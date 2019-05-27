<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Customers - Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css"/>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">Customers Management</h2>
		<h3>
			<a href="customer_form.jsp">Create New Customer</a>
		</h3>
	</div>
	<!--============================================-->
	<div class = "message" align="center">
		<h4>
			${message}
		</h4>
	</div>
	<!--============================================-->
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>E-mail</th>
				<th>Full Name</th>
				<th>City</th>
				<th>Country</th>
				<th>Registered Date</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="customer" items="${listCustomer}" varStatus="status">>
			<tr>
					<td>${status.index+1}</td>
					<td>${customer.customerId}</td>
					<td>${customer.email}</td>
					<td>${customer.fullname}</td>
					<td>${customer.city}</td>
					<td>${customer.country}</td>
					<td>${customer.registerDate}</td>
					<td><a href="edit_customer?id=${customer.customerId}">Edit</a> <a
						href="javascript:void(0)" class="deleteLink" id="${customer.customerId}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<!--============================================-->
	<br />
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				customerId = $(this).attr("id");
				if (confirm('Are you sure you want to delete the customer with ID: '
						+ customerId + ' ?')) {
					window.location = 'delete_customer?id='+customerId;
				}
			});
		});
	});
	</script>
</body>
</html>
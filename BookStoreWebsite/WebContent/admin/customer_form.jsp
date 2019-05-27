<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Customer</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${customer!=null}">
		Edit Customer</c:if>
			<c:if test="${customer==null}">
		Create New Customer</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${customer!=null}">
			<form action="update_customer" method="post" id="customerForm">
				<input type="hidden" name="customerId"
					value="${customer.customerId}" />
		</c:if>

		<c:if test="${customer==null}">
			<form action="create_customer" method="post" id="customerForm">
		</c:if>
		<table class="form">
			<tr>
				<td align="left">E-mail Address:</td>
				<td align="left"><input type="text" id="email" name="email"
					value="${customer.email}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Full Name:</td>
				<td align="left"><input type="text" id="fullname"
					name="fullname" value="${customer.fullname}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Password:</td>
				<td align="left"><input type="password" id="password"
					name="password" value="${customer.password}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Confirm Password:</td>
				<td align="left"><input type="password" id="confirmPassword"
					name="confirmPassword" value="${customer.password}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Phone Number:</td>
				<td align="left"><input type="text" id="phone"
					name="phone" value="${customer.phoneNumber}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Address:</td>
				<td align="left"><input type="text" id="address" name="address"
					value="${customer.address}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">City:</td>
				<td align="left"><input type="text" id="city" name="city"
					value="${customer.city}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Zip Code:</td>
				<td align="left"><input type="text" id="zipCode" name="zipCode"
					value="${customer.zipCode}" size="45" /></td>
			</tr>
			<tr>
				<td align="left">Country:</td>
				<td align="left"><input type="text" id="country" name="country"
					value="${customer.country}" size="45" /></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button type="button" id="cancelButton">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<br />
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#customerForm").validate({
			rules : {
				email : {
					required : true,
					email : true
				},
				fullname : "required",
				password : "required",
				confirmPassword : {
					required : true,
					equalTo : "#password"
				},
				phone : "required",
				address : "required",
				city : "required",
				zipCode : "required",
				country : "required",
			},
			messages : {
				email : {
					required : "Please Enter Email",
					email : "Please Enter an Valid Email address"
				},
				fullname : " Please Enter Full Name",
				password : " Please Enter Password",
				confirmPassword : {
					required : " Please Enter Confritm Password",
					equalTo : " Confirm Password does not match password"
				},
				phone : " Please Enter Phone Number",
				address : " Please Enter Address",
				city : " Please Enter City",
				zipCode : " Please Enter Zip Code",
				country : " Please Enter Country",
			}
		});

		$("#cancelButton").click(function() {
			history.go(-1);
		});
	});
</script>
</html>
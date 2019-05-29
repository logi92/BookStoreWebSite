<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Customer Profile</title>
<link rel="stylesheet" href="css/style.css" />
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>

	<div align="center">
		<h2 class="pageheading">Edit My Profile</h2>
	</div>

	<div align="center">
		<form action="update_customer" method="post" id="customerForm">
			<table class="form">
				<tr>
					<td align="right">E-mail Address:</td>
					<td align="left"><b>${loggedCustomer.email}</b> <i>(Cannot Be Changed)</i></td>
				</tr>
				<tr>
					<td align="right">Full Name:</td>
					<td align="left"><input type="text" id="fullname"
						name="fullname" value="${loggedCustomer.fullname}" size="45" /></td>
				</tr>
				<tr>
					<td align="right">Phone Number:</td>
					<td align="left"><input type="text" id="phone" name="phone"
						value="${loggedCustomer.phoneNumber}" size="45" /></td>
				</tr>
				<tr>
					<td align="right">Address:</td>
					<td align="left"><input type="text" id="address"
						name="address" value="${loggedCustomer.address}" size="45" /></td>
				</tr>
				<tr>
					<td align="right">City:</td>
					<td align="left"><input type="text" id="city" name="city"
						value="${loggedCustomer.city}" size="45" /></td>
				</tr>
				<tr>
					<td align="right">Zip Code:</td>
					<td align="left"><input type="text" id="zipCode"
						name="zipCode" value="${loggedCustomer.zipCode}" size="45" /></td>
				</tr>
				<tr>
					<td align="right">Country:</td>
					<td align="left"><input type="text" id="country"
						name="country" value="${loggedCustomer.country}" size="45" /></td>
				</tr>
				<tr><td colspan="2" align="center"><i>(Leave password fields blank if you don't want to change password)</i></td></tr>
				<tr>
					<td align="right">Password:</td>
					<td align="left"><input type="password" id="password"
						name="password" size="45" /></td>
				</tr>
				<tr>
					<td align="right">Confirm Password:</td>
					<td align="left"><input type="password" id="confirmPassword"
						name="confirmPassword" size="45" /></td>
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
				fullname : "required",
				confirmPassword : {
					equalTo : "#password"
				},
				phone : "required",
				address : "required",
				city : "required",
				zipCode : "required",
				country : "required",
			},
			messages : {
				fullname : " Please Enter Full Name",
				confirmPassword : {
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
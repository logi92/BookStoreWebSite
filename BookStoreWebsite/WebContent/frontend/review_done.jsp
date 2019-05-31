<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Review Posted - Online Bookstore</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<table class="normal" width="40%">
			<tr>
				<td><h2>Your Review</h2></td>
				<td>&nbsp;</td>
				<td>${loggedCustomer.fullname}</td>
			</tr>
			<tr>
				<td colspan="3"><hr /></td>
			</tr>
			<tr>
				<td><span id="book-title">${book.title}</span><br /> <img
					class="book-large" src="data:image/jpg;base64,${book.base64Image}" />
				</td>
				<td colspan="2">
					<h3>Your Review has been posted. Thank you!</h3>
				</td>
			</tr>
			<tr>
				<td colspan="3" align="center">
					<button id="buttonCancel">Cancel</button>
				</td>
			</tr>
			<tr>
				<td></td>
			</tr>
		</table>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonCancel").click(function() {
			window.history.back();
		});
	});
</script>
</html>
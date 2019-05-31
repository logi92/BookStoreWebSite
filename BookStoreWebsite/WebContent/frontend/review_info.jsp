<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Write Review - Online Bookstore</title>
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<form id="reviewForm">
			<table class="normal" width="40%">
				<tr>
					<td><h3>You already wrote a review for this book</h3></td>
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
					<td>
						<div id="rateYo"></div>  
						<br /> 
						<input type="text" name="headline" id="headline" size = "49" value="${review.headline}" readonly="readonly">
						<br/>
						<br /> 
						<textarea readonly="readonly" rows="5" cols="50">${review.comment}</textarea>
					</td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#rateYo").rateYo({
			rating : ${review.rating},
			fullStar:true,
			starWidth : "25px",
			readOnly:true
		});
	});
</script>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Review</title>
<link rel="stylesheet" href="../css/style.css" />
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br />

	<div align="center">
		<h2 class="pageheading">Edit Review</h2>
	</div>

	<div align="center">
		<form action="update_review" method="post" id="reviewForm">
			<input type="hidden" name="reviewId" value="${review.reviewId}" />
			<table border="1">
				<tr>
					<td align="right">Book:</td>
					<td align="left"><b>${review.book.title}</b></td>
				</tr>
				<tr>
					<td align="right">Rating:</td>
					<td align="left"><b>${review.rating}</b></td>
				</tr>
				<tr>
					<td align="right">Customer:</td>
					<td align="left"><b>${review.customer.fullname}</b></td>
				</tr>
				<tr>
					<td align="right">HeadLine:</td>
					<td align="left"><input type="text" name="headLine" id="headLine" value="${review.headline}" size="49"/></td>
				</tr>
				<tr>
					<td align="right">Comment:</td>
					<td align="left"><textarea rows="5" cols="50" name="comment" id="comment">${review.comment}</textarea></td>
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
		$("#reviewForm").validate({
			rules : {
				headLine : "required",
				comment : "required",
			},
			messages : {
				headLine : " Please Enter HeadLine",
				comment : " Please Enter Comment",
			}
		});

		$("#cancelButton").click(function() {
			history.go(-1);
		});
	});
</script>
</html>
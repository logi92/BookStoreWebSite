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
<script type="text/javascript" src="js/jquery.validate.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.css">
<script src="https://cdnjs.cloudflare.com/ajax/libs/rateYo/2.3.2/jquery.rateyo.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<div align="center">
		<form id="reviewForm" action="submit_review" method="post">
			<table class="normal" width="40%">
				<tr>
					<td><h2>Your Review</h2></td>
					<td>&nbsp;</td>
					<td>${loggedCustomer.fullname}</td>
				</tr>
				<tr>
					<td colspan="3"><hr/></td>
				</tr>
				<tr>
					<td>
						<span id="book-title">${book.title}</span><br/>
						<img class="book-large" src="data:image/jpg;base64,${book.base64Image}" />
					</td>
					<td>
						<div id="rateYo"></div>
						<input type="hidden" id="rating" name="rating"/>
						<input type="hidden" name="bookId" value="${book.bookId}"/>
						<br/>
						<input type="text" name="headline" id="headline" size = "49" placeholder="Headline or summary for your review (required)">
						<br/><br/>
						<textarea name="comment" id="comment" rows="10" cols="50" placeholder="Write your opinion (required)"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="3" align="center">
					<button type="submit">Submit</button>
					&nbsp;&nbsp;&nbsp;
					<button id="buttonCancel">Cancel</button>
					</td>
				</tr>
				<tr>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		 $("#buttonCancel").click(function() {
			 window.history.back();
		});
		 
		$("#reviewForm").validate({
			rules : {
				headline : "required",
				comment : "required",
			},
			messages : {
				headline : "Please Enter Headline",
				comment : "Please Enter Comment",
			},
		});
		
		 $("#rateYo").rateYo({
			    fullStar: true,
			    starWidth: "25px",
			    onSet: function(rating,rateYoInstance){
			    	$("#rating").val(rating);
			    }
		});
		
	});
</script>
</html>
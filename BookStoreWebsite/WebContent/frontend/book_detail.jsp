<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>${book.title}-OnlineBookStore</title>
<link rel="stylesheet" type="text/css" href="css/style.css">
<script type="text/javascript" src="js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<div class="center">

		<table class="book">
			<tr>
				<td colspan="3" align="left">
					<p id="book-title">${book.title}</p> by <span id="author">${book.author}</span>
				</td>
			</tr>
			<tr>
				<td rowspan="2"><img class="book-large"
					src="data:image/jpg;base64,${book.base64Image}" /></td>
				<td valign="top" align="left"><jsp:directive.include
						file="book_rating.jsp" /> <a href="#reviews">${fn:length(book.reviews)}
						Reviews</a></td>
				<td valign="top" rowspan="2" width="20%">
					<h2>$${book.price}</h2> <br />
					<button type="submit">Add To Cart</button>
				</td>
			</tr>
			<tr>
				<td id="description">${book.description}</td>
			</tr>
			<tr>
				<td><a id="reviews"><h2>Customer Reviews</h2></a></td>
				<td colspan="2" align="center">
					<button id="buttonWriteReview">Write a Customer Review</button>
				</td>
			</tr>

			<tr>
				<td colspan="3" align="left">
					<table class="normal">
						<c:forEach items="${book.reviews}" var="review">
							<tr>
								<td><c:forTokens items="${review.stars}" delims=","
										var="star">
										<c:if test="${star eq 'on'}">
											<img src="images/rating-on.png">
										</c:if>
										<c:if test="${star eq 'off'}">
											<img src="images/rating-off.png">
										</c:if>
									</c:forTokens> - <b>${review.headline}</b></td>
							</tr>
							<tr>
								<td>by ${review.customer.fullname} on <fmt:formatDate
										type="date" value="${review.reviewTime}" /></td>
							</tr>
							<tr>
								<td><i>${review.comment}</i></td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</td>
			</tr>
		</table>

	</div>
	<jsp:include page="footer.jsp" />
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#buttonWriteReview").click(function() {
			window.location='write_review?book_id='+${book.bookId};
		});
	});
</script>
</html>
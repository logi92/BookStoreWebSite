<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Evergreen Books - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<div class="center">

		<div>
			<h2>New Books</h2>
			<c:forEach items="${listBooks}" var="book">
				<div class="book">
					<div>
						<a href="view_book?id=${book.bookId}"> <img class="book-small" 
							src="data:image/jpg;base64,${book.base64Image}"/>
						</a>
					</div>
					<div>
						<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
						</a>
					</div>
					<div>Rating*****</div>
					<div>
						<i>by ${book.author}</i>
					</div>
					<div>
						<b>$${book.price}</b>
					</div>
				</div>
			</c:forEach>
		</div>
		<div class="next_row">
			<h2>Best-Selling Books</h2>
		</div>
		<div class="next_row">
			<h2>Favorite Books</h2>
		</div>
	</div>
	<br />
	<br />
	<jsp:include page="footer.jsp" />
</body>
</html>
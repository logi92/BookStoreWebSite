<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Books in ${category.name} - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="center">
		<h2>${category.name}</h2>
	</div>

	<div class="book_group">
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
				<div>
					<jsp:directive.include file="book_rating.jsp"/>
				</div>
				<div>
					<i>by ${book.author}</i>
				</div>
				<div>
					<b>$${book.price}</b>
				</div>
			</div>
		</c:forEach>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>
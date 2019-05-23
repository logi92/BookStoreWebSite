<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Result for ${keyword} Evergreen Books - Online Book Store</title>
<link rel="stylesheet" href="css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<div align="center">
		<c:if test="${fn:length(result)==0}">
			<h2>No Results For "${keyword}"</h2>
		</c:if>

		<c:if test="${fn:length(result)>0}">
			<div align="center" style="width: 80%; margin: 0 auto;">
				<center><h2>Results for: "${keyword}"</h2></center>
				<c:forEach items="${result}" var="book">

					<div>
						<div style="display: inline-block; margin: 20px; width: 10%" align="left">
							<div>
								<a href="view_book?id=${book.bookId}"> <img
									src="data:image/jpg;base64,${book.base64Image}" width="128"
									height="164" />
								</a>
							</div>
						</div>
						<div
							style="display: inline-block; margin: 20px; vertical-align: top; width: 60%"
							align="left">
							<div>
								<h2>
									<a href="view_book?id=${book.bookId}"> <b>${book.title}</b>
									</a>
								</h2>
							</div>
							<div>Rating*****</div>
							<div>
								<i>by ${book.author}</i>
							</div>
							<div>${fn:substring(book.description,0,100)}...</div>
						</div>
						<div style="display: inline-block; margin: 20px; vertical-align: top;">
							<h3><b>$${book.price}</b></h3>
							<h3><a href="">Add To Cart</a></h3>
						</div>
					</div>

				</c:forEach>
			</div>
		</c:if>
	</div>
	<br />
	<br />
	<jsp:include page="footer.jsp" />
</body>
</html>
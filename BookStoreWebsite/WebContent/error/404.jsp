<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Page Not Found Error</title>
</head>
<body>
	<div align="center">
		<div>
			<img
				src="${pageContext.request.contextPath}/images/BookstoreAdminLogo.png" />
		</div>
		<div>
			<h2>Sorry, This Page Could Not Be Found.</h2>
		</div>
		<div>
			<a href="javascript:history.go(-1)">Go Back</a>
		</div>
	</div>
</body>
</html>
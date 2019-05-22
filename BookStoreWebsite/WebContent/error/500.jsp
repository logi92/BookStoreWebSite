<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Internal Server Error</title>
</head>
<body>
	<div align="center">
		<div>
			<img
				src="${pageContext.request.contextPath}/images/BookstoreAdminLogo.png" />
		</div>
		<div>
			<h2>Sorry, The Server Has Encounteret an error. Please Check
				Back Later</h2>
		</div>
		<div>
			<a href="javascript:history.go(-1)">Go Back</a>
		</div>
	</div>
</body>
</html>
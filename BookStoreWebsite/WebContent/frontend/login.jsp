<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<jsp:include page="header.jsp"/>
	<br/>
	<br/>
	<div align="center">
		<h2>Please login:</h2>
		<form>
		Email: <input type="text" size="10"><br/>
		Password: <input type="password" size="10"><br/>
		<input type="submit" value="Login">
		</form>
	</div>
	<br/>
	<br/>
<jsp:include page="footer.jsp"/>
</body>
</html>
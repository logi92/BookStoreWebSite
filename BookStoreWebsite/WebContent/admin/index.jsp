<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bookstore Administration</title>
<link rel="stylesheet" href = "../css/style.css">
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
<!--============================================-->	
	<div align="center">
		<h2 class="pageheading">Administrative Dashboard</h2>
	</div>
<!--============================================-->
	<div align="center">
		<hr width="50%" />
		<h2 class="pageheading">Quick Actions:</h2>
		<b>
		<a href="1">New Book</a> |
		<a href="2">New User</a> |
		<a href="3">New Category</a> |
		<a href="4">New Customer</a>
		</b>
	</div>
<!--============================================-->		
	<div align="center">
		<hr width="50%"/>
		<h2 class="pageheading">Recent Sales:</h2>
	</div>
<!--============================================-->	
	<div align="center">
		<hr width="50%"/>
		<h2 class="pageheading">Recent Reviews:</h2>
	</div>
<!--============================================-->
	<div align="center">
		<hr width="50%"/>
		<h2 class="pageheading">Statistics:</h2>
		<hr width="50%"/>
	</div>
<!--============================================-->
	<br />
	<jsp:include page="footer.jsp" />
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Manage Categories - Bookstore Administration</title>
<link rel="stylesheet" href="../css/style.css"/>
<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<!--============================================-->
	<div align="center">
		<h2 class="pageheading">Categories Management</h2>
		<h3>
			<a href="category_form.jsp">Create New Category</a>
		</h3>
	</div>
	<!--============================================-->
	<div class = "message" align="center">
		<h4>
			${message}
		</h4>
	</div>
	<!--============================================-->
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Index</th>
				<th>ID</th>
				<th>Name</th>
				<th>Actions</th>
			</tr>
			<c:forEach var="cat" items="${listCategory}" varStatus="status">>
			<tr>
					<td>${status.index}</td>
					<td>${cat.categoryId}</td>
					<td>${cat.name}</td>
					<td><a href="edit_category?id=${cat.categoryId}">Edit</a> <a
						href="javascript:void(0)" class="deleteLink" id="${cat.categoryId}">Delete</a></td>
				</tr>
			</c:forEach>

		</table>
	</div>

	<!--============================================-->
	<br />
	<jsp:include page="footer.jsp" />
	<script type="text/javascript">
	
	$(document).ready(function(){
		$(".deleteLink").each(function(){
			$(this).on("click",function(){
				categoryId = $(this).attr("id");
				if (confirm('Are you sure you want to delete the category with ID: '
						+ categoryId + ' ?')) {
					window.location = 'delete_category?id='+categoryId;
				}
			});
		});
	});
	</script>
</body>
</html>
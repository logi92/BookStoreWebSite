<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create New Book</title>
<link rel="stylesheet" href="../css/style.css" />
<link rel="stylesheet" href="../css/jquery-ui.min.css" />

<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="../css/richtext.min.css">


<script type="text/javascript" src="../js/jquery-3.4.1.min.js"></script>
<script type="text/javascript" src="../js/jquery.richtext.min.js"></script>
<script type="text/javascript" src="../js/jquery.validate.min.js"></script>
<script type="text/javascript" src="../js/jquery-ui.min.js"></script>
</head>
<body>
	<jsp:include page="header.jsp"></jsp:include>
	<br />

	<div align="center">
		<h2 class="pageheading">
			<c:if test="${book!=null}">
		Edit book</c:if>
			<c:if test="${book==null}">
		Create New Book</c:if>
		</h2>
	</div>

	<div align="center">
		<c:if test="${book!=null}">
			<form action="update_book" method="post" id="bookForm"
				enctype="multipart/form-data">
				<input type="hidden" name="bookId" value="${book.bookId}" />
		</c:if>

		<c:if test="${book==null}">
			<form action="create_book" method="post" id="bookForm"
				enctype="multipart/form-data">
		</c:if>
		<table class="form">
			<tr>
				<td align="right">Category:</td>
				<td align="left"><select name="category">
						<c:forEach items="${listCategory}" var="category">
							<c:if test="${category.categoryId eq book.category.categoryId}">
								<option value="${category.categoryId}" selected>
							</c:if>
							<c:if test="${category.categoryId ne book.category.categoryId}">
								<option value="${category.categoryId}">
							</c:if>
							${category.name}</option>
						</c:forEach>
				</select></td>
			</tr>
			<tr>
				<td align="right">Title:</td>
				<td align="left"><input type="text" id="title" name="title"
					value="${book.title}" size="20" /></td>
			</tr>
			<tr>
				<td align="right">Author:</td>
				<td align="left"><input type="text" id="author" name="author"
					value="${book.author}" size="20" /></td>
			</tr>
			<tr>
				<td align="right">ISBN:</td>
				<td align="left"><input type="text" id="isbn" name="isbn"
					value="${book.isbn}" size="20" /></td>
			</tr>
			<tr>
				<td align="right">Publish Date:</td>
				<td align="left"><input type="text" id="publishDate"
					name="publishDate"
					value='<fmt:formatDate pattern="MM/dd/yyyy" value="${book.publishDate}"/>' /></td>
			</tr>
			<tr>
				<td align="right">Book Image:</td>
				<td align="left"><input type="file" id="bookImage"
					name="bookImage" size="20" /> <br /> <img id="thumbnail"
					alt="Image Preview" style="width: 20%; margin-top: 10px"
					src="data:image/jpg;base64,${book.base64Image}"></td>
			</tr>
			<tr>
				<td align="right">Price:</td>
				<td align="left"><input type="text" id="price" name="price"
					value="${book.price}" size="10" /></td>
			</tr>
			<tr>
				<td align="right">Description:</td>
				<td align="left">
				<textarea rows="5" cols="50" name="description" id="description">${book.description}</textarea></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2" align="center">
					<button type="submit">Save</button>&nbsp;&nbsp;&nbsp;
					<button type="button" id="cancelButton">Cancel</button>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<br />
	<jsp:include page="footer.jsp"></jsp:include>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#publishDate").datepicker();
		
		$('#description').richText();


		$("#bookImage").change(function() {
			showImageThumbnail(this);
		});

		$("#bookForm").validate({
			rules : {
				title : "required",
				author : "required",
				isbn : "required",
				publishDate : "required",
				
				<c:if test="${book==null}">
					bookImage : "required",
				</c:if>
				
				price : "required",
				description : "required",
			},
			messages : {
				title : " Please Enter Title Of The Book",
				author : " Please Enter Author Of The Book",
				isbn : " Please Enter ISBN Of The Book",
				publishDate : " Please Enter Publish Date Of The Book",
				bookImage : " Please Choose Book Image",
				price : " Please Enter Price Of The Book",
				description : " Please Enter Description Of The Book",
			}
		});

		$("#cancelButton").click(function() {
			history.go(-1);
		});
	});

	function showImageThumbnail(fileInput) {
		var file = fileInput.files[0];

		var reader = new FileReader();

		reader.onload = function(e) {
			$("#thumbnail").attr('src', e.target.result);
		};
		reader.readAsDataURL(file);
	};
</script>
</html>
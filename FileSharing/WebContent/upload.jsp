<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>File Explorer</title>

<!-- Bootstrap core CSS -->
<link href="styles/bootstrap.min.css" rel="stylesheet">
<!-- Bootstrap theme -->
<link href="styles/bootstrap-theme.min.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="theme.css" rel="stylesheet">
</head>

<body role="document">
	<div class="container theme-showcase" role="main">
		<ul class="nav nav-tabs">
			<li role="presentation"><a href="<%=getServletContext().getContextPath() %>/ListFilesServlet">Explorer</a></li>
			<li role="presentation" class="active"><a href="upload.jsp">File
					Upload</a></li>
		</ul>
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<a href="<%=getServletContext().getContextPath()%>/LogoutServlet">
				<span class="glyphicon glyphicon-log-out"></span>
			</a>
			<h1>File Upload</h1>
			<p>This is the main page where you can upload files.</p>
		</div>


		<div class="page-header">
			<form action="UploadServlet" method="post"
				enctype="multipart/form-data">
				<h1>Upload a File</h1>
				<label class="control-label">File Upload</label> <input multiple
					id="input-25" type="file" name="file" class="file-loading"><br>
				<button class="btn btn-primary" type="submit">Submit</button>
			</form>
		</div>
	</div>
	<!-- /container -->


	<!-- Bootstrap core JavaScript
    ================================================== -->
	<!-- Placed at the end of the document so the pages load faster -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<script src="scripts/bootstrap.min.js"></script>
</body>
</html>
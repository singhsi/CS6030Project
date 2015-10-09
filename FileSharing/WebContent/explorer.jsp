<%@page import="com.fileshare.util.RemovePrefix"%>
<%@page import="com.amazonaws.services.s3.model.S3ObjectSummary"%>
<%@page import="com.amazonaws.services.s3.model.ObjectListing"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="FileExplorer">
<meta name="author" content="Simranjit">
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
	<%
		ObjectListing userOL = (ObjectListing) request.getSession()
				.getAttribute("userObjListing");
		ObjectListing sharedOL = (ObjectListing) request.getSession()
				.getAttribute("sharedObjListing");
		String userPrefix = userOL.getPrefix();
		String sharedPrefix = sharedOL.getPrefix();
	%>
	<div class="container theme-showcase" role="main">
		<ul class="nav nav-tabs">
			<li role="presentation" class="active"><a href="explorer.jsp">Explorer</a></li>
			<li role="presentation"><a href="upload.jsp">File Upload</a></li>
		</ul>
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<a href="<%=getServletContext().getContextPath()%>/LogoutServlet">
				<span class="glyphicon glyphicon-log-out"></span>
			</a>
			<h1>File Explorer</h1>
			<p>This is the main page where you can view yours and shared
				files.</p>
		</div>

		<div class="page-header">
			<h2>User's files</h2>
		</div>
		<form action="<%=getServletContext().getContextPath()%>/ShareServlet" method="post">
			<table class="table table-striped">
				<thead>
					<tr>
						<th>File Name</th>
						<th>Share</th>
						<th>Unshare</th>
					</tr>
				</thead>
				<tbody>
					<%
						for (S3ObjectSummary s3OS : userOL.getObjectSummaries()) {
							String name = RemovePrefix.removePrefix(userPrefix,
									s3OS.getKey());
							if (s3OS != null && !name.isEmpty()) {
					%>
					<tr>
						<td><a
							href="<%=getServletContext().getContextPath()%>/DownloadServlet?filename=<%=name%>"><%=name%></a></td>
						<td><input type="checkbox" name="share" value="<%=name%>"></td>
						<td><input type="checkbox" name="unshare" value="<%=name%>"></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			<button class="btn btn-primary" type="submit">Update Sharing</button>
		</form>
		<div class="page-header">
			<h2>Shared files</h2>
		</div>
		<table class="table table-striped">
			<thead>
				<tr>
					<th>File Name</th>
				</tr>
			</thead>
			<tbody>
				<%
					for (S3ObjectSummary s3OS : sharedOL.getObjectSummaries()) {
						String name = RemovePrefix.removePrefix(sharedPrefix,
								s3OS.getKey());
						if (s3OS != null && !name.isEmpty()) {
				%>
				<tr>
					<td><a
						href="<%=getServletContext().getContextPath()%>/DownloadServlet?filename=<%=name%>"><%=name%></a></td>
				</tr>
				<%
					}
					}
				%>
			</tbody>
		</table>
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
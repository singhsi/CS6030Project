<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
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
<title>Login Page</title>

<!-- Bootstrap core CSS -->
<link href="styles/bootstrap.min.css" rel="stylesheet">

</head>

<body>
<%
	String sessionUser = (String) request.getSession().getAttribute("user");
	if(sessionUser != null){
		RequestDispatcher rd = request.getRequestDispatcher("explorer.jsp");
		rd.forward(request, response);
	}
%>
	<div class="container">
		<!-- Main jumbotron for a primary marketing message or call to action -->
		<div class="jumbotron">
			<h1>File Share</h1>
			<form class="form-signin" action="LoginServlet" method="post">
				<h2 class="form-signin-heading">Please sign in</h2>
				<label for="inputEmail" class="sr-only">Email address</label> <input
					type="text" id="inputEmail" name="username" class="form-control"
					placeholder="username" required autofocus> <label
					for="inputPassword" class="sr-only">Password</label> <input
					type="password" name="password" id="inputPassword" class="form-control"
					placeholder="Password" required>
				<div class="checkbox">
					<label> <input type="checkbox" value="remember-me">
						Remember me
					</label>
				</div>
				<button class="btn btn-lg btn-primary btn-block" type="submit">Sign
					in</button>
			</form>
		</div>
	</div>
	<!-- /container -->
</body>
</html>
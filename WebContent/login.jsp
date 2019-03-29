<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login Page</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<link rel="icon" href="images/favicon.png" type="image/png"
	sizes="16x16">
</head>
<header>
	<h1>Player CRUD</h1>
</header>
<body>
	<ul class="topnav">
		<li><a class="active" href="login.jsp">Home</a></li>
		<li><a href="signup.jsp">Sign Up</a></li>
		<li><a href="view">View All</a></li>
		<li><a href="search.jsp">Search</a></li>
		<li><a href="update.jsp">Update</a></li>
		<li><a href="delete.jsp">Delete</a></li>
		<li class="right"><a href="about.html">About</a></li>
	</ul>
	<br>
	<form action="login" onsubmit="return isValidUserCredentials()"
		method="post">
		<h2>Player Login</h2>
		<label for="email">Email: </label><br> <input type="text"
			id="email" name="email" placeholder="example@ex.com"><br>
		<br> <label for="password">Password: </label><br> <input
			type="password" id="password" name="password"
			placeholder="6-20 digits or numbers only"><br> <br>
		<div class="submit">
			<input type="submit" value="Login"> &nbsp;&nbsp;&nbsp;&nbsp;<input
				type="reset" value="Cancel " onclick="clearErr()" /><br> <br>
			<span id="errMsg" style="color: red;"></span>
			<%
				if (request.getAttribute("errorMessage") != null) {
			%>
			<span id="errorMessage" style="color: red;"><%=request.getAttribute("errorMessage")%></span>
			<%
				request.removeAttribute("errorMessage");
				}
			%>
		</div>
		<p>
			Don't have an account? <a href="signup.jsp">Sign up here!</a>
		</p>
	</form>
	<script type="text/javascript" src="scripts/validate.js"></script>
</body>
<footer>
	<br> Copyright © 2019 Honda Huang
</footer>
</html>
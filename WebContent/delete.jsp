<%@page import="com.to.Player"%>
<%@page import="java.util.List"%>
<%@taglib prefix="st" uri="/WEB-INF/playertags.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Account</title>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
<link rel="icon" href="images/favicon.png" type="image/png"
	sizes="16x16">
</head>
<header>
	<h1>Player CRUD</h1>
</header>
<body>
	<ul class="topnav">
		<li><a href="login.jsp">Home</a></li>
		<li><a href="signup.jsp">Sign Up</a></li>
		<li><a href="view">View All</a></li>
		<li><a href="search.jsp">Search</a></li>
		<li><a href="update.jsp">Update</a></li>
		<li><a class="active" href="delete.jsp">Delete</a></li>
		<li class="right"><a href="logout">Log Out</a></li>
	</ul>
	<br>
	<%
		if (session != null && session.getAttribute("email") != null) {
	%>
	<form action="delete" onsubmit="return isPlayerDeleteValid()">
		<h2>Delete Account</h2>
		<label for="id">Player ID: </label><br> <input type="text"
			id="id" name="id"><br> <br>
		<div class="submit">
			<input type="submit" value="Delete"><br> <br> <span
				id="errMsg" style="color: red;">Warning: Deleting your own account will log you out automatically</span>
		</div>
		<%
			if (request.getAttribute("errorMessage") != null) {
		%>
		<span id="errorMessage" style="color: red;"><%=request.getAttribute("errorMessage")%></span>
		<%
			request.removeAttribute("errorMessage");
				}
		%>
	</form>
	<br>
	<%
		if (session != null && session.getAttribute("playerList") != null) {
				String[] headers = (String[]) session.getAttribute("headers");
				List<Player> playerList = (List<Player>) session.getAttribute("playerList");
	%>
	<h2 class="white">Player Deleted:</h2>
	<br>
	<st:playerResults playerList="<%=playerList%>" headers="<%=headers%>" />
	<%
		session.removeAttribute("playerList");
			}
	%>
	<%
		} else {
	%>
	<h2 class="warn" align="center" style="color: red;">Please login
		first</h2>
	<%
		session.removeAttribute("playerList");
		}
	%>
	<script type="text/javascript" src="scripts/validate.js"></script>
</body>
<footer>
	<br> Copyright © 2019 Honda Huang
</footer>
</html>
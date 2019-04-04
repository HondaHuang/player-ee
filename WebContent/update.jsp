<%@page import="com.to.Player"%>
<%@page import="java.util.List"%>
<%@taglib prefix="st" uri="/WEB-INF/playertags.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update Contact</title>
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
		<li><a class="active" href="update.jsp">Update</a></li>
		<li><a href="delete.jsp">Delete</a></li>
		<li class="right"><a href="logout">Log Out</a></li>
	</ul>
	<br>
	<%
		if (session != null && session.getAttribute("email") != null) {
	%>
	<form action="update" onsubmit="return isPlayerUpdateValid()">
		<h2>Update Contact</h2>
		<label for="id">ID: </label><br> <input type="text" id="id"
			name="id"><br> <br> <label for="contact">New
			Contact Number: </label><br> <input type="text" id="contact"
			name="contact"><br> <br>
		<div class="submit">
			<input type="submit" value="Update"> &nbsp;&nbsp;&nbsp;&nbsp;<input
				type="reset" value="Cancel " onclick="clearErr()" /><br> <br>
			<span id="errMsg" style="color: red;"></span>
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
	<h2 class="white">Player Contact Updated:</h2>
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
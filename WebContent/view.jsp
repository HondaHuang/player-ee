<%@page import="com.to.Team"%>
<%@page import="java.util.List"%>
<%@page import="com.to.Player"%>
<%@taglib prefix="st" uri="/WEB-INF/playertags.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View Tables</title>
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
		<li><a class="active" href="view">View All</a></li>
		<li><a href="search.jsp">Search</a></li>
		<li><a href="update.jsp">Update</a></li>
		<li><a href="delete.jsp">Delete</a></li>
		<li class="right"><a href="logout">Log Out</a></li>
	</ul>
	<%
		if (session != null && session.getAttribute("email") != null) {
	%>
	<%
		if (request.getAttribute("errorMessage") != null) {
	%>
	<span id="errorMessage" style="color: red;"><%=request.getAttribute("errorMessage")%></span>
	<%
		request.removeAttribute("errorMessage");
			}
	%>
	<br>
	<%
		if (session != null && session.getAttribute("playerList") != null) {
				String[] headers = (String[]) session.getAttribute("headers");
				List<Player> playerList = (List<Player>) session.getAttribute("playerList");
	%>
	<h2 class="white">Player Table</h2>
	<br>
	<st:playerResults playerList="<%=playerList%>" headers="<%=headers%>" />
	<%
		} else {
	%>
	<h2 class="warn">Please come from the navigation bar</h2>
	<%
		}
	%>
	<br>
	<br>
	<%
		if (session != null && session.getAttribute("teamList") != null) {
				String[] teamheaders = (String[]) session.getAttribute("teamheaders");
				List<Team> teamList = (List<Team>) session.getAttribute("teamList");
	%>
	<h2 class="white">Team Table</h2>
	<br>
	<st:teamResults teamList="<%=teamList%>" headers="<%=teamheaders%>" />
	<%
		session.removeAttribute("playerList");
				session.removeAttribute("teamList");
			}
	%>
	<%
		} else {
	%>
	<br>
	<h2 class="warn" align="center" style="color: red;">Please login
		first</h2>
	<%
		session.removeAttribute("playerList");
			session.removeAttribute("teamList");
		}
	%>
</body>
<footer>
	<br> Copyright © 2019 Honda Huang
</footer>
</html>
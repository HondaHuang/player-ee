<%@page import="com.to.Player"%>
<%@page import="java.util.List"%>
<%@taglib prefix="st" uri="/WEB-INF/playertags.tld"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Search Page</title>
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
		<li><a class="active" href="search.jsp">Search</a></li>
		<li><a href="update.jsp">Update</a></li>
		<li><a href="delete.jsp">Delete</a></li>
		<li class="right"><a href="about.html">About</a></li>
	</ul>
	<br>
	<form action="search" onsubmit="return isPlayerSearchValid()">
		<h2>Player Search</h2>
		<select id="option" name="searchoption" onchange="searchChange()">
			<option value="1">Search by ID</option>
			<option value="2">Search by Name</option>
			<option value="3">Search by Email</option>
			<option value="4">Search by Date of Birth</option>
			<option value="5">Search by Contact</option>
			<option value="6">Search by Gender</option>
			<option value="7">Search by Team</option>
		</select> <br> <br>
		<div id="search">
			<label for="id">ID: </label><br> <input type="text" id="id"
				name="searchValue">
		</div>
		<div id="teamsearch">
			<label for="team">Team: </label><br>
			<sql:setDataSource var="teamList" driver="oracle.jdbc.OracleDriver"
				url="jdbc:oracle:thin:@localhost:1521:xe" user="javaus"
				password="javaus" />
			<sql:query dataSource="${teamList}" var="result">
				            SELECT teamname FROM teams
				        </sql:query>
			<select id="team" name="teamname">
				<option></option>
				<c:forEach var="row" items="${result.rows}">
					<option>
						<c:out value="${row.teamname}" />
					</option>
				</c:forEach>
			</select>
		</div>
		<br>
		<div class="submit">
			<input type="submit" value="Search"><br> <br> <span
				id="errMsg" style="color: red;"></span>
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
	<st:playerResults playerList="<%=playerList%>" headers="<%=headers%>" />
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
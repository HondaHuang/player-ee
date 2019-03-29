<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sign Up Page</title>
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
		<li><a class="active" href="signup.jsp">Sign Up</a></li>
		<li><a href="view">View All</a></li>
		<li><a href="search.jsp">Search</a></li>
		<li><a href="update.jsp">Update</a></li>
		<li><a href="delete.jsp">Delete</a></li>
		<li class="right"><a href="about.html">About</a></li>
	</ul>
	<br>
	<div class="signup">
		<form id="form1" action="" method="post"
			onsubmit="return isValidPlayerSignUp()">
			<h2>Player Sign Up</h2>
			<label for="name">First Name: </label><br> <input type="text"
				id="name" name="name" placeholder="3-20 letters"><br> <br>
			<label for="dob">Date of Birth:</label> <br> <input type="date"
				id="dob" name="t_dob"><br> <br> <label
				for="gender">Gender: </label><br> <select id="gender"
				name="gender">
				<option></option>
				<option value="M">Male</option>
				<option value="F">Female</option>
			</select><br> <br> <label for="contact">Contact Number: </label><br>
			<input type="text" id="contact" name="contact"
				placeholder="10 digit number"><br> <br> <label
				for="team">Team: </label><br>
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
			</select> <br> <br> <label for="email">Email (Username): </label><br>
			<input type="text" id="email" name="email"
				placeholder="example@ex.com"><br> <br> <label
				for="password">Password: </label><br> <input type="password"
				id="password" name="password"
				placeholder="6-20 digits or numbers only"><br> <br>
			<label for="password2">Reenter Password: </label><br> <input
				type="password" id="password2"><br> <br>
			<div class="submit">
				<input type="submit" name="register">&nbsp;&nbsp;&nbsp;&nbsp;<input
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
		<%
			if (request.getParameter("register") != null) {
		%>
		<jsp:useBean id="player" class="com.to.Player" scope="session"></jsp:useBean>
		<jsp:setProperty property="*" name="player" />
		<%-- <jsp:forward page="signup"></jsp:forward> --%>
		<%
			response.sendRedirect("signup");
			}
		%>
		<form id="form2" action="" method="post"
			onsubmit="return isValidTeamSignUp()">
			<h2>Team Sign Up</h2>
			<label for="teamname">Team Name: </label><br> <input type="text"
				id="teamname" name="teamname"><br> <br> <label
				for="coachname">Coach Name: </label><br> <input type="text"
				id="coachname" name="coachname"><br> <br>
			<div class="submit">
				<input type="submit" name="registerteam">&nbsp;&nbsp;&nbsp;&nbsp;<input
					type="reset" value="Cancel " onclick="clearErr2()" /><br> <br>
				<span id="errMsg2" style="color: red;"></span>
			</div>
			<%
				if (request.getAttribute("errorMessage2") != null) {
			%>
			<span id="errorMessage2" style="color: red;"><%=request.getAttribute("errorMessage2")%></span>
			<%
				request.removeAttribute("errorMessage2");
				}
			%>
		</form>
		<%
			if (request.getParameter("registerteam") != null) {
		%>
		<jsp:useBean id="team" class="com.to.Team" scope="session"></jsp:useBean>
		<jsp:setProperty property="*" name="team" />
		<%-- <jsp:forward page="teamsignup"></jsp:forward> --%>
		<%
			response.sendRedirect("teamsignup");
			}
		%>
	</div>
	<script type="text/javascript" src="scripts/validate.js"></script>
</body>
<footer> Copyright © 2019 Honda Huang </footer>
</html>
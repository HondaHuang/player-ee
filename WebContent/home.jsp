<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Home Page</title>
</head>
<body>
	<%
		if (session != null && session.getAttribute("email") != null) {
	%>
	<h1>
		Signed in as
		<%=session.getAttribute("email")%></h1>
	<h2>
		Logged in successfully at
		<%=new Date()%></h2>
	<a href="logout">LogOff</a>
	<%
		} else {
	%>
	<h2 align="center" style="color: red;">Please login first</h2>
	<%
		}
	%>
</body>
</html>
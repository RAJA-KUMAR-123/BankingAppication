<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<% 
	String message=(String)session.getAttribute("message");
	out.println(message);
%>
	<a href="HomePage.jsp"> Go back to homepage.</a>
	
</body>
</html>
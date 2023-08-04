<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Display Loan Details</title>
<style>
body {
	font-family: Arial, sans-serif;
	background-color: #f0f0f0;
	margin: 0;
	padding: 0;
	display: flex;
	justify-content: center;
	align-items: center;
	min-height: 100vh;
	background-size: cover;
	background-position: center;
}

.container {
	max-width: 800px;
	padding: 30px;
	background-color: rgba(255, 255, 255, 0.9);
	border-radius: 10px;
	box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

th, td {
	padding: 15px;
	text-align: center;
	border: 1px solid #ccc;
}

th {
	background-color: #3366cc;
	color: #fff;
}

tr:nth-child(even) {
	background-color: #f2f2f2;
}

a {
	display: block;
	margin-top: 20px;
	text-align: center;
	color: #3366cc;
	text-decoration: none;
	font-weight: bold;
	transition: color 0.3s;
}

a:hover {
	color: #005580;
}
</style>
</head>
<body>
	<%
	session = request.getSession();

	int lid = (Integer) session.getAttribute("lid");

	String l_type = (String) session.getAttribute("l_type");

	int tenure = (Integer) session.getAttribute("tenure");

	int interest = (Integer) session.getAttribute("interest");

	String description = (String) session.getAttribute("description");
	%>
	<div class="container">
		<table>
			<tr>
				<th>Loan ID</th>
				<th>Loan Type</th>
				<th>Tenure</th>
				<th>Interest</th>
				<th>Description</th>
			</tr>
			<tr>
				<td><%=lid%></td>
				<td><%=l_type%></td>
				<td><%=tenure%></td>
				<td><%=interest%></td>
				<td><%=description%></td>
			</tr>
		</table>
		<a href="HomePage.jsp">Go Back</a>
	</div>
</body>
</html>
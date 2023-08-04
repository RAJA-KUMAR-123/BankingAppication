<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Transfer History</title>
<style>
body {
	font-family: Arial, sans-serif;
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
	Statement stmt = null;

	Connection con = null;

	ResultSet resultSet = null;

	PreparedStatement pstmt = null;

	int accno = (int) session.getAttribute("accno");

	try {

		Class.forName("com.mysql.cj.jdbc.Driver");

		String url = "jdbc:mysql://localhost:3306/Project1";

		String user = "root";

		String pwd = "Rahulg01*";

		con = DriverManager.getConnection(url, user, pwd);

		String sql = "select * from transferstatus where sender_accno=? or receiver_accno=?";

		pstmt = con.prepareStatement(sql);

		pstmt.setInt(1, accno);

		pstmt.setInt(2, accno);

		resultSet = pstmt.executeQuery();
	%>
	<div class="container">
		<table>
			<tr>
				<th>Transfer ID</th>
				<th>Customer ID</th>
				<th>Bank Name</th>
				<th>IFSC</th>
				<th>Sender Account Number</th>
				<th>Receiver IFSC</th>
				<th>Receiver Account Number</th>
				<th>Amount</th>
			</tr>
			<%
			while (resultSet.next()) {
			%>
			<tr>
				<td><%=resultSet.getInt(1)%></td>
				<td><%=resultSet.getInt(2)%></td>
				<td><%=resultSet.getString(3)%></td>
				<td><%=resultSet.getString(4)%></td>
				<td><%=resultSet.getInt(5)%></td>
				<td><%=resultSet.getString(6)%></td>
				<td><%=resultSet.getInt(7)%></td>
				<td><%=resultSet.getInt(8)%></td>
			</tr>
			<%
			}
			} catch (Exception e) {
			out.println(e);
			}
			%>
		</table>
		<a href="HomePage.jsp">Go to Home Page</a>
	</div>
</body>
</html>